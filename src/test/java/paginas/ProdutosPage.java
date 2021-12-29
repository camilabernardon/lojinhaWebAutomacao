package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProdutosPage {
    private WebDriver navegador;

    public ProdutosPage(WebDriver navegador) {
        this.navegador = navegador;
    }

    public AdicionarProdutoPage clicarEmAdicionarProduto(){
        navegador.findElement(By.linkText("ADICIONAR PRODUTO")).click();
        return new AdicionarProdutoPage(navegador);
    }

    public String capurarMensagemApresenta(){
       return navegador.findElement(By.cssSelector(".toast.rounded")).getText();
    }
}
