package sucursal.modelo;

import sucursal.exceptions.CajaNoAbiertaException;
import sucursal.exceptions.CompraNoIniciadaException;

public class EstadoCajaCerrada implements EstadoCaja {
	@Override
	public boolean estaAbierta() {
		return false;
	}

	@Override
	public boolean estaCerrada() {
		return true;
	}

	@Override
	public boolean estaComprando() {
		return false;
	}

	@Override
	public void checkPuedeAbrir() {
		return;
	}

	@Override
	public void checkPuedeCerrar() {
		throw new CajaNoAbiertaException();
	}

	@Override
	public void checkPuedeIniciarCompra() {
		throw new CajaNoAbiertaException();
	}

	@Override
	public void checkPuedeTerminarCompra() {
		throw new CompraNoIniciadaException();
	}

}