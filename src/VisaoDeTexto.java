import java.awt.Color;
import java.util.Map;

public class VisaoDeTexto implements VisaoSimulador {
    private EstatisticasCampo estatisticas;

    private final String PREFIXO_PASSO = "Passo: ";
    private final String PREFIXO_POPULACAO = "Populacao: ";


    public VisaoDeTexto() {
        estatisticas = new EstatisticasCampo();
    }

    @Override
    public void definirCor(Class<?> classeAnimal, Color cor) {
        //Nada a fazer
    }

    @Override
    public boolean ehViavel(Campo campo) {
       return estatisticas.ehViavel(campo);
    }

    @Override
    public void mostrarStatus(int passo, Campo campo) {
        estatisticas.reiniciar();
        System.out.println(PREFIXO_PASSO + passo);
        System.out.println(PREFIXO_POPULACAO + estatisticas.obterDetalhesPopulacao(campo));

        

    
        estatisticas.finalizarContagem();
        
    }

    @Override
    public void reiniciar() {
        //estatisticas.reiniciar();

    }

    @Override
    public void reabilitarOpcoes() {
        //Nada a fazer
    }

    
}
