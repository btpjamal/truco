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
    int decisaoPc(String cartapc1, String cartapc2, String cartapc3, String cartaJogador, List<String> ordemForca){

        // comparar entre as cartas

        List<String> cartasPc = Arrays.asList(cartapc1, cartapc2, cartapc3);

        //Mapeia cada carta ao seu índice de força
        Map<String , Integer> mapaForcas = cartasPc.stream()
                .collect(Collectors.toMap(Function.identity(), ordemForca::indexOf));

        // Ordena as cartas pela força

        List<String> ordenadas = cartasPc.stream()
                .sorted(Comparator.comparingInt(mapaForcas::get))
                .collect(Collectors.toList());

        String fraca = ordenadas.get(0);
        String intermediária = ordenadas.get(1);
        String forte = ordenadas.get(2);

        String cartaEscolhida;
        int forcaRival = ordemForca.indexOf(cartaJogador);
        int forcaForte= ordemForca.indexOf(forte);
        int forcaIntermediaria= ordemForca.indexOf(intermediária);
        int forcaFraca = ordemForca.indexOf(fraca);

        // se a carta fraca já vence, joga ela
        if (ordemForca.indexOf(fraca) > forcaRival){
            cartaEscolhida= fraca;
        }
        // se a intermediária vence, joga ela
        else if (ordemForca.indexOf(intermediária) > forcaRival) {
            cartaEscolhida = intermediária;
        }
        // se só a forte vence, joga ela
        else if (ordemForca.indexOf(forte) > forcaRival) {
            cartaEscolhida = forte;
        }
        // se nenhuma vence, descarta a mais fraca
        else {
            cartaEscolhida = fraca;
        }

        return ordemForca.indexOf(cartaEscolhida);
    }
}