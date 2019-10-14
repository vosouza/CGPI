package dados;

public class ListaPrimitivos {
	private Object vetor[];
    private int qtd;

    public ListaPrimitivos(){
        setVetor(null);
        setQtd(0);
    }

    public Object[] getVetor() {
        return vetor;
    }

    public int getQtd() {
        return qtd;
    }

    public void setVetor(Object[] vetor) {
        this.vetor = vetor;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public void inserir(Object obj){
        if (vetor == null){          
            setVetor(new Object[1]);
            vetor[0] = obj; 
            setQtd(getQtd()+1);
        }
        else {
            Object aux[] = new Object[vetor.length+1];
            copiar(vetor, aux);
            aux[aux.length-1] = obj;
            setVetor(aux);    
            setQtd(getQtd()+1);

        }
    }

    public void remover (int i) {
        if(vetor != null){
            vetor[i] = null;

            if(getQtd() > 1){
                Object aux[] = new Object[vetor.length-1];
                copiar(vetor, aux);
                setVetor(aux);   
                setQtd(getQtd() - 1);
            } else {
                setVetor(null);  
                setQtd(0);
            }
        }
    }

    public Object buscar (int i){
        return vetor[i];
    }

    public boolean vazia(){
        return (getQtd()==0 && getVetor() == null);
    }

    private void copiar(Object origem[], Object destino[]){
        int i, k = 0;
        for (i = 0; i < origem.length; i++){
            if (origem[i] != null) {
                destino[k] = origem[i];
                k++;
            }
        }       
    }

    public String toString(){
        String s = "";
        if(vetor != null){
            for (int i = 0; i < vetor.length; i++){
                s += vetor[i].toString();
            }
        }
        return s;
    }
}
