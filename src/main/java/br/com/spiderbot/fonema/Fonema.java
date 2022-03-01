package br.com.spiderbot.fonema;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Implementação d algorítimo BuscarBR para pesquisa fonética no idioma português.
 * 
 * @author $Author$
 * @author $Committer$
 * @version $Branch$
 * @see <a href="https://github.com/LVRodrigues/jfonemabr/blob/main/docs/NOVOB.pdf">NOVOB.pdf</a>
 */
public class Fonema {

    /**
     * Lista de caracteres acentuados que devem ser substituidos.
     * <p>
     * Como o primeiro passo para obter o fonema é passar o texto para maiúsculo,
     * não há necessidade de tratar os caracteres minúsculos acentuados.
     * <p>
     * A primeira dimensão possui a lista de caracteres acentuados. A segunda
     * dimensão poussui os caracteres de substituição.
     */
    private static final char[][] INVALID_CHARS = {
        {
            'Á', 'É', 'Í', 'Ó', 'Ú',
            'À', 'È', 'Ì', 'Ò', 'Ù',
            'Â', 'Ê', 'Î', 'Ô', 'Û',
            'Ã', 'Ẽ', 'Ĩ', 'Õ', 'Ũ',
            'Ä', 'Ë', 'Ï', 'Ö', 'Ü',
            'Ç', 'Ÿ', 'Ý', 'Ñ'
        },
        {
            'A', 'E', 'I', 'O', 'U',
            'A', 'E', 'I', 'O', 'U',
            'A', 'E', 'I', 'O', 'U',
            'A', 'E', 'I', 'O', 'U',
            'A', 'E', 'I', 'O', 'U',
            'C', 'Y', 'Y', 'N'
        }
    };

    /**
     * Índice da coluna de pesquisa da matriz de caracteres inválidos.
     */
    private static final int INVALID_SEARCH_INDEX   = 0;

    /**
     * Índice da coluna de troca de valores da matriz de caracteres inválidos.
     */
    private static final int INVALID_REPLACE_INDEX  = 1;

    /**
     * Expressão regular para ignorar os caracteres de A até Z e de 0 (zero) até 9 (nove)
     * e remover os demais caracteres.
     */
    private static final Pattern INVALID_PATTERN = Pattern.compile("[^A-Z0-9\s]");

    /**
     * Conjunto de expressões regulares (primeira dimensão) e seus caracteres de 
     * substituição (segunda dimensão).
     */
    private static final Object[][] REPLACES = {
        {Pattern.compile("(BL|BR)"),                    "B"},
        {Pattern.compile("(PH)"),                       "F"},
        {Pattern.compile("(GL|GR|MG|NG|RG)"),           "G"},
        {Pattern.compile("Y"),                          "I"},
        {Pattern.compile("(GE|GI|RJ|MJ)"),              "J"},
        {Pattern.compile("(CA|CO|CU|CK)|Q"),            "K"},
        {Pattern.compile("N"),                          "M"},
        {Pattern.compile("(AO|AUM|GM|MD|OM)"),          "M"},
        {Pattern.compile("(PR)"),                       "P"},
        {Pattern.compile("L"),                          "R"},
        {Pattern.compile("(CE|CI|CH|CS|RS|TS|X|Z)"),    "S"},
        {Pattern.compile("(TR|TL)"),                    "T"},
        {Pattern.compile("(CT|RT|ST|PT)"),              "T"},
        {Pattern.compile("W|\\bU"),                     "V"},
        {Pattern.compile("RM"),                         "SM"},
        {Pattern.compile("[MRS]\\b"),                   ""},
        {Pattern.compile("[AEIOUH]"),                   ""}
    };

    /**
     * Índice da coluna de expressões regulares para pesquisa de fonemas.
     */
    private static final int REPLACE_PATTERN_INDEX    = 0;

    /**
     * Índice da coluna de troca de valores para a pesquisa de fonemas.
     */
    private static final int REPLACE_VALUE_INDEX      = 1;

    /**
     * Construtor oculto, tornando uma classe utilitária.
     */
    private Fonema() {
    }

    /**
     * Processa um texto aplicando as regras do algorítimo BuscarBR.
     * 
     * @param text Texto para extrair os fonemas.
     * @return Fonemas.
     */
    public static String process(String text) {
        // 1. Converter para maiúsculas.
        String result = text.toUpperCase();
        // 2. Remover caracteres especiais e acentos:
        result = clean(result);
        // 3. Trocar dados por letras de fonéticas:
        result = replaces(result);
        // 4. Remover caracteres duplicados consecutivamente.
        result = duplicates(result);
        // 5. Remover vogais? Realizado no final do passo 3 utilizando expressões regulares.
        return result;
    }    

    /**
     * Remove caracteres duplicados consecutivamente.
     * 
     * @param text Texto para processar.
     * @return Fonema.
     */
    private static String duplicates(String text) {
        char current            = 0;
        StringBuilder result    = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (current != c) {
                result.append(c);
            }
            current = c;
        }
        return result.toString();
    }

    /**
     * Troca letras baseadas em expressões regulares.
     * @param text Texto para manipular.
     * @return
     */
    private static String replaces(String text) {
        String result = text;
        for (int i = 0; i < REPLACES.length; i++) {
            Matcher matcher = ((Pattern) REPLACES[i][REPLACE_PATTERN_INDEX]).matcher(result);
            result          = matcher.replaceAll((String) REPLACES[i][REPLACE_VALUE_INDEX]);
        }
        return result;
    }

    /**
     * Limpa os caracteres acentuados e especiais do texto.
     * @param text Texto para ser limpo.
     * @return Texto manipulado.
     */
    private static String clean(String text) {
        String result = text;
        for (int i = 0; i < INVALID_CHARS[INVALID_SEARCH_INDEX].length; i++) {
            result = result.replace(INVALID_CHARS[INVALID_SEARCH_INDEX][i], 
                                    INVALID_CHARS[INVALID_REPLACE_INDEX][i]);
        }
        // Removendo os demais caracteres especiais:
        Matcher matcher = INVALID_PATTERN.matcher(result);
        return matcher.replaceAll("");
    }
}
