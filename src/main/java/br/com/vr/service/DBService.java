package br.com.vr.service;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vr.emun.Status;
import br.com.vr.entity.ItemVO;
import br.com.vr.entity.PedidoVO;
import br.com.vr.repository.ItemRepository;
import br.com.vr.repository.PedidoRepository;

@Service
public class DBService {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	public void instantiateTesDataBase() throws ParseException {
		PedidoVO ped1 = new PedidoVO(null, Status.ATIVO, new Date());
		PedidoVO ped2 = new PedidoVO(null, Status.ATIVO, new Date());
		PedidoVO ped3 = new PedidoVO(null, Status.ATIVO, new Date());
		
		ItemVO item1 = new ItemVO(null, "Item 1", ped1);
		ItemVO item2 = new ItemVO(null, "Item 2", ped1);
		ItemVO item3 = new ItemVO(null, "Item 3", ped2);
		ItemVO item4 = new ItemVO(null, "Item 4", ped2);
		ItemVO item5 = new ItemVO(null, "Item 5", ped2);
		ItemVO item6 = new ItemVO(null, "Item 6", ped3);
		ItemVO item7 = new ItemVO(null, "Item 7", ped3);
		
		ped1.getItens().addAll(Arrays.asList(item1,item2));
		ped2.getItens().addAll(Arrays.asList(item3,item4,item5));
		ped3.getItens().addAll(Arrays.asList(item6,item7));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2,ped3));
		itemRepository.saveAll(Arrays.asList(item1,item2,item3,item4,item5,item6,item7));
	}
}
