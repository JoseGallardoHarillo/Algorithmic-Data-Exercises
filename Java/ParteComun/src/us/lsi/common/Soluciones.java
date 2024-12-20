package us.lsi.common;

import java.util.*;

import us.lsi.common.Sets2;


/**
 * Un agregado de elementos ordenados
 *  
 * @author Miguel Toro 
 *
 * @param <S> El tipo de los elementos
 */
public class Soluciones<S> {
	
	
	private SortedSet<S> soluciones;

    private Soluciones(Comparator<S> cmp){
    	soluciones = Sets2.<S>newTreeSet(cmp);
    }

    public static <S extends Comparable<? super S>> Soluciones<S> empty() {
		return new Soluciones<S>(Comparator.<S>naturalOrder());
	}
    
    public static <S> Soluciones<S> empty(Comparator<S> cmp) {
		return new Soluciones<S>(cmp);
	}
    
    public boolean isEmpty(){
    	return soluciones.isEmpty();
    }
    
    public int size(){
    	return soluciones.size();
    }
    
    public boolean add(S s){
    	boolean r = soluciones.add(s);
    	return r;
    }

	public S getMejorSolucion() {
		S r = null;
		if(!soluciones.isEmpty()){
			r =soluciones.first();
		}
		return r;
	}
	
	public SortedSet<S> getSoluciones() {
		return soluciones;
	}
	
	public String toString(){
		String r = "El n�mero de Soluciones es = "+soluciones.size()+".  Las Soluciones son: \n";
		for(S s : soluciones){
			r=r+s+"\n";
		}
		return r;
	}
}
