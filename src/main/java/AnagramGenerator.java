import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AnagramGenerator {

    /**
     * @param input recebe a string em qual será gerado os anagramas.
     * @return a lista de strings referentes aos anagramas gerados.
     * <p>
     * Antes de se gerar os anagramas é realizado validações para quando o input está vazia ou null.
     * Seguido da validação para quando o input é somente um caracter, não sendo possível gerar anagrama assim
     * Por último a validação se o input tem somente letras.
     * <p>
     * Após validado é realizado a conversão de string para um array de char,
     * uma vez que com o array de char é mais fácil de se manipular para anagramas.
     * Após converter para char, é chamado o metodo permute para gerar as permutações possíveis do array de caracteres.
     */
    public static List<String> generateAnagrams(String input) throws SingleCharacterException {
        List<String> result = new ArrayList<>();

        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("A entrada está vazia.");
        }
        if (input.length() == 1) {
            throw new SingleCharacterException("Entrada contém somente 1 caracter, não sendo possível criar anagramas.");
        }
        if (!input.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("A entrada deve conter apenas letras.");
        }

        char[] chars = input.toCharArray();
        permute(chars, 0, result);
        return result;
    }

    /**
     * @param chars  array dos caracteres que serão permutados.
     * @param index  o indice atual que está sendo permutado.
     * @param result a lista que armazena os anagramas gerados.
     *               <p>
     *               É realizado validação em cima do último indice gerado, para saber se aquele array de caracteres já foi permutado.
     *               Caso ainda não tenha sido permutado, é realizado um loop para cada indice i, partindo do indice atual index,
     *               utilizando o metodo swap, que está responsável por trocar as posições entre os caracteres,
     *               após realizar o primeiro swap é realizado a permutação, seguido de um novo swap para retornar ao
     *               estado original antes da próxima iteração.
     */
    private static void permute(char[] chars, int index, List<String> result) {
        if (index == chars.length - 1) {
            result.add(new String(chars));
            return;
        }
        for (int i = index; i < chars.length; i++) {
            swap(chars, index, i);
            permute(chars, index + 1, result);
            swap(chars, index, i);
        }
    }

    /**
     * @param array de caracteres a serem trocados.
     * @param i     indice do primeiro caracter a ser trocado
     * @param j     indice do segundo caracter a ser trocado
     */
    private static void swap(char[] array, int i, int j) {
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Recebe uma entrada via terminal, que é capturada pelo metodo Scanner, utilizando System.in.
     * Após receber a entrada, é utilizado nextline, para se seguir com os próximos passos, chamando a função generateAnagrams.
     * Com os anagramas recebidos, é então realizado um loop, para imprimir no terminal cada anagrama contido na lista.
     */
    public static void main(String[] args) throws SingleCharacterException {
        var scanner = new Scanner(System.in);
        System.out.print("Digite uma string: ");
        var input = scanner.nextLine();
        List<String> anagrams = generateAnagrams(input);
        System.out.println("Anagramas de '" + input + "':");
        anagrams.forEach(System.out::println);
        scanner.close();
    }
}
