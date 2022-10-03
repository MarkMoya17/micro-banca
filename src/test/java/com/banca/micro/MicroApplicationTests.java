package com.banca.micro;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.banca.micro.entity.ClienteEntity;
import com.banca.micro.service.IClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MicroApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IClienteService clienteService;

    ClienteEntity cliente;
    List<ClienteEntity> lstCliente = new ArrayList<>();
    
    @BeforeEach
    private void Clientes(){
        cliente = ClienteEntity
                .builder()
                .nombre("Ana")
                .genero("F")
                .edad(18)
                .identificacion("335553")
                .direccion("Paz 555")
                .telefono("984125436")
                .build();
        lstCliente.add(cliente);
    }
    
    @Test
    void crear() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(cliente);
        
        given(clienteService.crear(cliente)).willReturn(cliente);
        this.mockMvc.perform(post("/clientes")
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
    
    @Test
    void listar() throws Exception {
        given(clienteService.listar()).willReturn(lstCliente);
        this.mockMvc.perform(get("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

}
