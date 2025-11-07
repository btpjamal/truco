import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);




        List<String> valoresRestantes = Arrays.asList("4", "5", "6", "7", "Q", "J", "K", "A", "2", "3");
        List<String> naipes = Arrays.asList("♦","♠","♥","♣");


        // Manilhas fixas
        List<String> manilhas = Arrays.asList("4♣", "7♥", "A♠", "7♦");

        // ordem de força das cartas
        List<String> ordemForca = new ArrayList<>();

        // criando a lista de ordem de força
        for (String v : valoresRestantes){
            for (String n : naipes){
                String carta = v + n;
                if (!manilhas.contains(carta)){
                    ordemForca.add(carta);
                }
            }
        }
        // adiciona as manilhas no final (mais fortes)
        ordemForca.addAll(manilhas);



        List<String> cartas= new ArrayList<>();
        cartas.addAll(ordemForca);
        System.out.println(cartas);


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




        // é aleatório quem começa
        Random random = new Random();
        int pontosJogador= 0;
        int pontosComputador= 0;
        String quemVira= random.nextInt(2) == 0 ? "Jogador" : "Computador";

        while(pontosJogador < 2 && pontosComputador < 2){
            String cartaJogador, cartaPc;
            System.out.println("Rodada atual: "+ (pontosJogador + pontosComputador + 1));
            System.out.println("Quem vira: " + quemVira);


            if (quemVira.equals("Jogador")){


                System.out.println("Sua mão: "+ maoJogador);
                System.out.print("[");
                for (int i = 1; i <= maoJogador.size(); i++) {
                    System.out.print(i + (i < maoJogador.size() ? "][" : "]"));
                }
                System.out.println();
                int escolha= scan.nextInt();
                cartaJogador= maoJogador.get(escolha -1);
                maoJogador.remove(escolha -1);

                cartaPc = decisaoPc(maoComputador, cartaJogador, ordemForca);
                maoComputador.remove(cartaPc);
            }else {

                cartaPc = decisaoPc(maoComputador, "", ordemForca);
                maoComputador.remove(cartaPc);
                System.out.println(cartaPc);
                System.out.println("Sua mão: "+ maoJogador);
                System.out.print("[");
                for (int i = 1; i <= maoJogador.size(); i++) {
                    System.out.print(i + (i < maoJogador.size() ? "][" : "]"));
                }
                System.out.println();
                int escolha= scan.nextInt();
                cartaJogador= maoJogador.get(escolha -1);
                maoJogador.remove(escolha -1);
            }

            String vencedor = compararCartas(cartaJogador, cartaPc, ordemForca);
            if (vencedor.equals("Jogador")){
                pontosJogador++;
                quemVira= "Jogador";
            } else if (vencedor.equals("Computador")) {
                pontosComputador++;
                quemVira = "Computador";
            }
        }


        if (pontosJogador == 2) {
            System.out.println("🏆 Jogador venceu a partida!");
        } else {
            System.out.println("🏆 Computador venceu a partida!");
        }




    }
    static String compararCartas(String carta1, String carta2, List<String> ordemForca){
        int forca1= ordemForca.indexOf(carta1);
        int forca2= ordemForca.indexOf(carta2);

        System.out.println("Jogador: "+ carta1 + "| Computador: " + carta2);
        if (forca1 > forca2){
            System.out.println("Jogador venceu a rodada!");
            return "Jogador";
        } else if (forca1 < forca2) {
            System.out.println("Computador venceu a rodada!");
            return "Computador";
        } else {
            System.out.println("Empate!");
            return "Empate";
        }
    }
    static String decisaoPc(List<String> cartasPc, String cartaJogador, List<String> ordemForca){

        // mapeia cada carta ao seu indice de força
        Map<String, Integer> mapaForcas = cartasPc.stream()
                .collect(Collectors.toMap(Function.identity(), ordemForca::indexOf));


        // Ordena as cartas pela força

        List<String> ordenadas = cartasPc.stream()
                .sorted(Comparator.comparingInt(mapaForcas::get))
                .collect(Collectors.toList());

        if (ordenadas.size() == 1){
            return ordenadas.get(0);
        }

        String fraca = ordenadas.get(0);
        String intermediaria = ordenadas.size() > 2 ? ordenadas.get(1) : fraca;
        String forte = ordenadas.size() > 2 ? ordenadas.get(2) : ordenadas.get(ordenadas.size() -1);

        int forcaRival = cartaJogador.isEmpty() ? -1 : ordemForca.indexOf(cartaJogador);

        // se a carta fraca já vence, joga ela
        if (ordemForca.indexOf(fraca) > forcaRival){
            return fraca;
        }
        // se a intermediária vence, joga ela
        else if (ordemForca.indexOf(intermediaria) > forcaRival) {
            return intermediaria;
        }
        // se só a forte vence, joga ela
        else if (ordemForca.indexOf(forte) > forcaRival) {
            return forte;
        }
        // se nenhuma vence, descarta a mais fraca
        else {
            return fraca;
        }
    }
}