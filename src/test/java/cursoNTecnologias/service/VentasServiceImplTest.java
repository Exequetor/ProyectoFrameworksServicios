package cursoNTecnologias.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cursoNTecnologias.bd.domain.Productos;
import cursoNTecnologias.service.productos.ProductosService;
import cursoNTecnologias.service.ventas.VentasService;
import cursoNTecnologias.utils.Utils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class VentasServiceImplTest {
	@Inject
	VentasService ventasService;
	@Inject
	ProductosService productosService;
	
	@Test
	public void insertVenta (){
		System.out.println("\nTest: insertar nueva venta.");
		try{
			System.out.println("\nGenerando productos aleatorios.");
			int randomNumber;
			Productos producto;
			List<Productos> listaProductos = new ArrayList <Productos>();
			List<Productos> queryProductos = productosService.listarProductos();
			for (Productos prod : queryProductos) {
				randomNumber = Utils.randi(100);
				//30% de probabilidad de agregar un producto a la lista
				if (randomNumber < 30) {
					producto = new Productos ();
					producto.setIdproducto(prod.getIdproducto());
					producto.setMarcaid(prod.getMarcaid());
					producto.setNombre(prod.getNombre());
					producto.setPrecio(prod.getPrecio());
					producto.setPreciovta(prod.getPreciovta());
					producto.setCantidad(prod.getCantidad() - Utils.randi(prod.getCantidad()));
					listaProductos.add(producto);
				}
			}
			ventasService.insertVenta(1, listaProductos);
		}
		catch (Exception ex) {
			System.out.println("Error en VentaDaoImplTest->insertVenta");
			ex.printStackTrace();
		}
	}
}
