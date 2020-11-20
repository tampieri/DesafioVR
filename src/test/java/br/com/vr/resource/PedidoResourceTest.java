package br.com.vr.resource;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.vr.entity.PedidoVO;

@SpringBootTest
@AutoConfigureMockMvc
public class PedidoResourceTest {

	//@Autowired
	//private TestRestTemplate restTemplate;
	
	PedidoVO pedidoMock;
	Integer idPedido;
	
	@BeforeAll
    public void init() {
		pedidoMock = new PedidoVO();
    }
	
	
	@Test
	void contextLoads() {
		
	}
}
