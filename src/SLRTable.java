
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author diego
 */
public class SLRTable {

    private static boolean expressaoValida = true, leuCompletamente = false;
    public static Stack<String> pilhaParsing  = new Stack<String>();
    public static Stack<String> entradaPilha  = new Stack<String>();
    public static List<String> entradaParsing = new ArrayList<>();
    
    public static String[][] table = new String[][] {
       // 01  02   03  04  05  06  07  08  09   10 11 12 13 14 15 16 17
        {"","s9","","","","s5","","","s7","s8","","","1","3","3","2","","4"},
        {"","","","","","","","","","","acc","","","","","","",""},
        {"s10","","","r4","s11","","","","","","r4","","","","","","",""},
        {"","","","r2","","","","","","","r2","","","","","","",""},
        {"r7","","r7","r7","r7","","r7","","","","r7","","","","","","",""},
        {"","","","","","s5","","","s7","s8","","","","","","12","","4"},
        {"","","","r3","","","","","","","r3","","","","","","",""},
        {"r11","","r11","r11","r11","","r11","r11","","","r11","","","","","","",""},
        {"r11","","r12","r12","r12","","r12","r12","","","r12","","","","","","",""},
        {"","","","","","s5","","","s7","s8","","","","","","13","","4"},
        {"","s9","","","","s5","","","s7","s8","","","","14","6","15","","4"},
        {"","","","","","","","","s7","s8","","","","","","","16","17"},
        {"","","","","s11","","s18","","","","","","","","","","",""},
        {"","","s19","","s11","","","","","","","","","","","","",""},
        {"","","","r1","","","","","","","r1","","","","","","",""},
        {"","","","r4","s11","","","","","","r4","","","","","","",""},
        {"r6","","r6","r6","r6","","r6","s20","","","r6","","","","","","",""},
        {"r10","","r10","r10","r10","","r10","r10","","","r10","","","","","","",""},
        {"r8","","r8","r8","r8","","r8","","","","r8","","","","","","",""},
        {"","s9","","","","s5","","","s7","s8","","","21","3","6","2","","4"},
        {"","","","","","","","","s7","s8","","","","","","","","22"},
        {"","","","s23","","","","","","","","","","","","","",""},
        {"r9","","r9","r9","r9","","r9","r9","","","r9","","","","","","",""},
        {"","s9","","","","s5","","","s7","s8","","","24","3","6","2","","4"},
        {"","","","r5","","","","","","","r5","","","","","","",""}
    };
    
    public boolean verificaNumero(String entrada) throws RuntimeException{
        try {
            int i = Integer.parseInt(entrada);
            if(i >= 2 || i < 0){
                throw new RuntimeException("Token inválido");
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    public boolean verificaOperador(String operador){
        if(operador.equals(";"))
            return true;
        if(operador.equals("if"))
            return true;
        if(operador.equals("then"))
            return true;
        if(operador.equals("else"))
            return true;
        if(operador.equals("+"))
            return true;
        if(operador.equals("("))
            return true;
        if(operador.equals(")"))
            return true;
        if(operador.equals("*"))
            return true;
        if(operador.equals("$"))
            return true;
        else
            return false;
    }
    public String getTokenOperador(String entrada) throws RuntimeException{
        
           if(verificaOperador(entrada)){
               return entrada;
           }else{
               throw new RuntimeException("Token inválido");
           }
    }
    
    public void leEntrada(String entrada) throws RuntimeException{
        try{
            Stack<String> tempStack = new Stack<>();
            StringTokenizer st = new StringTokenizer(entrada, " ");
            while(st.hasMoreTokens()){
                String token = st.nextToken();
                if(verificaNumero(token)){
                    tempStack.push(token);
                }else{
                     System.out.println("Operador: " + getTokenOperador(token));
                     tempStack.push(token);
                }
            }
            
            while(!tempStack.isEmpty()){
                entradaPilha.push(tempStack.pop());
            }
            pilhaParsing.push("0");
        }catch(RuntimeException ex){
            throw new RuntimeException(ex.getMessage());
        }
       
    }   
    
    public void fazerParsing(ParsingTableModel object){
        ParsingTableModel model = object;
        
        // Obtem o indice da coluna pelo token de entrada
        int coluna = model.getColumnIndex(entradaPilha.peek());
        String operacao = (String) model.getValueAt(Integer.parseInt(pilhaParsing.peek()), coluna);
        System.out.println("Operacao: " + operacao);
        checaOperacao(operacao);
    }
    
    private void checaOperacao(String operacao){
        if(verificaNumero(operacao)){
            //Go to
        }else{
            if(operacao.charAt(0) == 's'){
                //Shift
                String replace = operacao.replace("s", "");
                operacaoShift(Integer.parseInt(replace));
            }else{
                //Reduce
                String replace = operacao.replace("r", "");
            }
        }
    }
    
    private void operacaoShift(int shift){
        pilhaParsing.add(String.valueOf(shift));
        entradaParsing.add(entradaPilha.pop());
    }
    
    private void operacaoReduce(int reduce){
        
    }
    
    public class Gramatica{
        Char 
    }
}
