package br.com.spiderbot.fonema;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Testes unitários para {@link Fonema}.
 * 
 * @author $Author$
 * @author $Committer$
 * @version $Branch$
 */
public class FonemaTest {

    /**
     * Matriz de palavras para tests fonéticos.
     */
    private static final String[] INPUTS = {
        "Luciano",
        "Que a força esteja com você",
        "Casamento",
        "Liquidação",
        "Horizonte",
        "Horizontal",
        "Melhoria",
        "Propagação"
    };

    /**
     * Matriz de resultados esperados para os testes fonéticos,
     * conforme a matriz INPUTS.
     */
    private static final String[] EXPECTEDS = {
        "RSM",
        "K FRK TJ K V",
        "KSMT",
        "RKDK",
        "RSMT",
        "RSMT",
        "MR",
        "PGK"
    };
    
    /**
     * Teste de processamento de fonemas, com palavras e fonemas conhecidos.
     * <p>
     * A matriz INPUTS possui os mesmos índices da matriz EXPECTEDS.
     */
    @Test
    public void testProcess() {
        for (int i = 0; i < INPUTS.length; i++) {
            String result = Fonema.process(INPUTS[i]);
            assertEquals(EXPECTEDS[i], result, String.format("Falha ao processar a palavra %s.", INPUTS[i]));
        }
    }

    /**
     * Teste de fonemas com palavras escritas de modo errado, mas sonoramente parecidas.
     */
    @Test
    public void testFejaum() {
        String feijao = Fonema.process("Feijão");
        String fejaum = Fonema.process("Fejaum");
        assertEquals(feijao, fejaum, "Falha no teste do feijão.");
    }
}
