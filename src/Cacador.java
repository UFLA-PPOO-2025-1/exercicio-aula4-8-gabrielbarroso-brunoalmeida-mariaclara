import java.util.List;
import java.util.Random;

public class Cacador implements Ator {
    private static final int NUMERO_TIROS_POR_PASSO = 3;

    private boolean ativo;
    private Campo campo;
    private Localizacao localizacao;

    private static final Random rand = Randomizador.obterRandom();

    public Cacador(Campo campo, Localizacao localizacao) {
        this.campo = campo;
        this.localizacao = localizacao;
        this.ativo = true;
        campo.colocar(this, localizacao);
    }

    @Override
    public void agir(List<Ator> novos) {
        if (!ativo) return;


        Localizacao novaLocal = obterLocalizacaoAleatoriaLivre();
        if (novaLocal != null) {
            campo.limpar(localizacao);
            localizacao = novaLocal;
            campo.colocar(this, localizacao);
        }

        for (int i = 0; i < NUMERO_TIROS_POR_PASSO; i++) {
            Localizacao alvo = obterLocalizacaoAleatoria();
            Object objeto = campo.obterObjetoEm(alvo);
            if (objeto instanceof Animal animal) {
                animal.morrer();
                campo.limpar(alvo); 
            }
        }
    }

    @Override
    public boolean estaAtivo() {
        return ativo;
    }

    public void morrer() {
        ativo = false;
        campo.limpar(localizacao);
    }

    public Localizacao obterLocalizacao() {
        return localizacao;
    }

    public Campo obterCampo() {
        return campo;
    }

    public void definirLocalizacao(Localizacao novaLocalizacao) {
        if (localizacao != null) {
            campo.limpar(localizacao);
        }
        localizacao = novaLocalizacao;
        campo.colocar(this, novaLocalizacao);
    }

    private Localizacao obterLocalizacaoAleatoriaLivre() {
        int tentativas = 100;
        for (int i = 0; i < tentativas; i++) {
            int linha = rand.nextInt(campo.obterComprimento());
            int coluna = rand.nextInt(campo.obterLargura());
            Localizacao loc = new Localizacao(linha, coluna);
            if (campo.obterObjetoEm(loc) == null) {
                return loc;
            }
        }
        return null; 
    }

    private Localizacao obterLocalizacaoAleatoria() {
        int linha = rand.nextInt(campo.obterComprimento());
        int coluna = rand.nextInt(campo.obterLargura());
        return new Localizacao(linha, coluna);
    }
}
