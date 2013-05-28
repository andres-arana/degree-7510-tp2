package sucursal.modelo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import sucursal.modelo.caja.Caja;
import sucursal.modelo.compras.Compra;
import sucursal.modelo.compras.ItemProducto;
import sucursal.modelo.ofertas.ProveedorOfertas;
import sucursal.modelo.productos.ProveedorProductos;
import sucursal.utilities.Observador;

@RunWith(MockitoJUnitRunner.class)
public class CompraTest {
	private Compra subject;

	@Mock
	private Observador<Compra> mockObservador;

	@Mock
	private Caja mockCaja;

	@Mock
	private ItemProducto mockLineProducto;

	@Mock
	private ProveedorOfertas mockProveedorOfertas;

	@Mock
	private ProveedorProductos mockProveedorProductos;

	@Before
	public void setup() {
		subject = new Compra(mockCaja, mockProveedorOfertas,
				mockProveedorProductos);
	}

	@Test
	public void agregarItemDeberiaNotificarItemsCambiados() {
		subject.getOnItemsCambiados().registrar(mockObservador);

		subject.agregarItem(mockLineProducto);

		Mockito.verify(mockObservador).notificar(subject);

	}

	@Test
	public void deshacerUltimoItemAgregadoDeberiaNotificarItemsCambiados() {
		subject.agregarItem(mockLineProducto);
		subject.getOnItemsCambiados().registrar(mockObservador);

		subject.quitarUltimoItemAgregado();

		Mockito.verify(mockObservador).notificar(subject);
	}

	@Test
	public void tieneItemsDevuelveTrueCuandoHayItems() {
		subject.agregarItem(mockLineProducto);

		assertThat(subject.tieneItems(), is(true));
	}

	@Test
	public void tieneItemsDevuelveFalsoCuandoNoHayItems() {
		assertThat(subject.tieneItems(), is(false));
	}
}
