package application;

import java.util.Stack;

public class Pilha {


	public void push(Object obj, Pilha pilha){
		pilha.push(obj);
	}
	
	public void pop(Object obj, Pilha pilha){
		pilha.pop();
	}
	public Object top(Pilha pilha){
		return pilha.lastElement();
	}
	
	public boolean isEmpty(Pilha pilha){
		return pilha.isEmpty();
	}
}
