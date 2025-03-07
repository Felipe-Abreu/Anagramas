import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnagramGeneratorTest {
    @Test
    void testGenerateAnagramsNormal() throws SingleCharacterException {
        List<String> anagrams = AnagramGenerator.generateAnagrams("abc");
        assertEquals(6, anagrams.size());
        assertTrue(anagrams.contains("abc"));
        assertTrue(anagrams.contains("acb"));
        assertTrue(anagrams.contains("bac"));
        assertTrue(anagrams.contains("bca"));
        assertTrue(anagrams.contains("cab"));
        assertTrue(anagrams.contains("cba"));
    }

    @Test
    void testGenerateAnagramsSingleLetter() {
        SingleCharacterException exception = assertThrows(
                SingleCharacterException.class,
                () -> AnagramGenerator.generateAnagrams("a")
        );

        assertEquals("Entrada contém somente 1 caracter, não sendo possível criar anagramas.", exception.getMessage());
    }

    @Test
    void testGenerateAnagramsInvalidInput() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> AnagramGenerator.generateAnagrams("ab25")
        );

        assertEquals("A entrada deve conter apenas letras.", exception.getMessage());
    }

    @Test
    void testGenerateAnagramsEmpty() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> AnagramGenerator.generateAnagrams("")
        );

        assertEquals("A entrada está vazia.", exception.getMessage());
    }


}