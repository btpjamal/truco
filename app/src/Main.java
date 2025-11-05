import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<String> valores = Arrays.asList("4","5","6","7","Q","J","K","A","2","3");
        List<String> naipes = Arrays.asList("♦","♠","♥","♣");

        List<String> cartas= new ArrayList<>();

        // criando a lista de cartas
        for (String v : valores){
            for (String n : naipes){
               cartas.add(v+n);
            }
        }
        System.out.println(cartas);

        // ordem de força das cartas
        List<String> ordemForca = new ArrayList<>();
        ordemForca.addAll(cartas);



        List<String> maoJogador = new ArrayList<>();
        List<String> maoComputador = new ArrayList<>();
        // dessa lista de cartas, pegar 3 aleatórias


        // implementar placar e rodada
        Collections.shuffle(cartas); // embaralhou
        String carta1=  cartas.remove(0);
        String carta2=  cartas.remove(1);
        String carta3=  cartas.remove(2);
        maoJogador.add(carta1);
        maoJogador.add(carta2);
        maoJogador.add(carta3);
        String cartapc1= cartas.remove(3);
        String cartapc2= cartas.remove(4);
        String cartapc3= cartas.remove(5);
        maoComputador.add(cartapc1);
        maoComputador.add(cartapc2);
        maoComputador.add(cartapc3);
        System.out.println("Mão Jogador: "+maoJogador);
        System.out.println("Mão Computador: "+maoComputador);
        compararCartas(carta1, cartapc1, ordemForca);

    }
    static void compararCartas(String carta1, String carta2, List<String> ordemForca){
        int forca1= ordemForca.indexOf(carta1);
        int forca2= ordemForca.indexOf(carta2);

        System.out.println("Jogador: "+ carta1 + "| Computador: " + carta2);
        if (forca1 > forca2){
            System.out.println("Jogador venceu a rodada!");
        } else if (forca1 < forca2) {
            System.out.println("Computador venceu a rodada!");
        } else {
            System.out.println("Empate!");
        }
    }
}