package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdicionarProdutoPage {
    private WebDriver navegador;

    public AdicionarProdutoPage(WebDriver navegador) {
        this.navegador = navegador;
    }

    public AdicionarProdutoPage informarNomeProduto (String produtoNome){
        navegador.findElement(By.id("produtonome")).sendKeys(produtoNome);
        return this;
    }

    public AdicionarProdutoPage informarValorProduto (String valorProduto){
        navegador.findElement(By.id("produtovalor")).sendKeys(valorProduto);
        return this;
    }

    public AdicionarProdutoPage informarCoresProduto (String coresProduto){
        navegador.findElement(By.id("produtocores")).sendKeys(coresProduto);
        return this;
    }

    public ProdutosPage clicarEmSalvarProdutoComErro (){
        navegador.findElement(By.cssSelector("button[type='submit']")).click();
        return new ProdutosPage(navegador);
    }

    public EditarProdutoPage clicarEmSalvarProdutoComSucesso (){
        navegador.findElement(By.cssSelector("button[type='submit']")).click();
        return new EditarProdutoPage(navegador);
    }
}
