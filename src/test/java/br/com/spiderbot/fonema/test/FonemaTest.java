package br.com.spiderbot.fonema.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.spiderbot.fonema.Fonema;

/**
 * Testes unitários para {@link Fonema}.
 * 
 * @author $Author$
 * @author $Committer$
 * @version $Branch$
 */
public class FonemaTest {

    /**
     * Matriz de palavras para teste (primeira dimensão) e resultados
     * fonéticos conhecidos (segunda dimensão).
     * <p>
     * Palavras com grafias erradas são propositais, utilizando a sonoridade
     * para testes.
     */
    private static final String[][] WORDS = {
        {"Luciano",         "RSM"},
        {"Broco",           "BK"},
        {"Block",           "BK"},
        {"Casa",            "KS"},
        {"Kasa",            "KS"},
        {"Sela",            "SR"},
        {"Cela",            "SR"},
        {"Circo",           "SRK"},
        {"Sirco",           "SRK"},
        {"Roça",            "RK"},
        {"Rosa",            "RS"},
        {"Você",            "V"},
        {"Ameixa",          "MS"},
        {"Ameicha",         "MS"},
        {"Toracs",          "TR"},
        {"Torax",           "TR"},
        {"Compactar",       "KMPT"},
        {"Compatar",        "KMPT"},
        {"Gana",            "GM"},
        {"Gene",            "JM"},
        {"Gibi",            "JB"},
        {"Gostar",          "GT"},
        {"Guabiru",         "GBR"},
        {"Gabiru",          "GBR"},
        {"Fleuma",          "FRM"},
        {"Fleugma",         "FRM"},
        {"Hieróglifo",      "RJF"},
        {"Hierogrifo",      "RJF"},
        {"Negro",           "MG"},
        {"Nego",            "MG"},
        {"Luminar",         "RM"},
        {"Ruminar",         "RM"},
        {"Mudez",           "MD"},
        {"Nudes",           "MD"},
        {"Comendo",         "KM"},
        {"Comeno",          "KM"},
        {"Bunginganga",     "BJG"},
        {"Bujiganga",       "BJG"},
        {"Philipe",         "FRP"},
        {"Felipe",          "FRP"},
        {"Queijo",          "KJ"},
        {"Kejo",            "KJ"},
        {"Lagarto",         "RGT"},
        {"Largato",         "RGT"},
        {"Perspectiva",     "PSPTV"},
        {"Pespectiva",      "PSPTV"},
        {"Lagartixa",       "RGTS"},
        {"Lagarticha",      "RGTS"},
        {"Largatixa",       "RGTS"},
        {"Mesmo",           "MSM"},
        {"Mermo",           "MSM"},
        {"Virgem",          "VJ"},
        {"Vige",            "VJ"},
        {"Superstição",     "SPTK"},
        {"Supertição",      "SPTK"},
        {"Contrato",        "KMT"},
        {"Contlato",        "KMT"},
        {"Walter",          "VT"},
        {"Valter",          "VT"},
        {"Exceder",         "SD"},
        {"Esceder",         "SD"}
    };

    /**
     * Índice das palavras para teste.
     */
    private static final int WORDS_TEST_INDEX = 0;

    /**
     * Índice dos fonemas de resultados conhecidos.
     */
    private static final int WORDS_RESULT_INDEX = 1;

    @Test
    public void testProcess() {
        for (int i = 0; i < WORDS.length; i++) {
            String result = Fonema.process(WORDS[i][WORDS_TEST_INDEX]);
            assertEquals(WORDS[i][WORDS_RESULT_INDEX], result,
                        String.format("Erro ao processar a palavra %s.", WORDS[i][WORDS_TEST_INDEX]));
        }
    }
}
