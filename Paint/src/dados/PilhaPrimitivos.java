package dados;
import java.util.Stack;

public class PilhaPrimitivos {

	private Stack<Object> pilha;	
	
	public PilhaPrimitivos(){
		pilha = new Stack<Object>();
	}
	
	public void push(Object obj){
		pilha.push(obj);
	}
	
	public void pop(){
		pilha.pop();
	}
	public Object top(){
		return pilha.lastElement();
	}
	
	public boolean isEmpty(){
		return pilha.isEmpty();
	}
}
