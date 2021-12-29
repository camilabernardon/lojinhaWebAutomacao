package modulos.produto;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import paginas.LoginPage;

import java.time.Duration;

@DisplayName("Teste Web do modulo de produtos")
public class ProdutoTest {

    private WebDriver navegador;

    @BeforeEach
    public void beforeEach(){
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver96\\chromedriver.exe");
        this.navegador = new ChromeDriver();
        this.navegador.manage().window().maximize();
        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        this.navegador.get("http://165.227.93.41/lojinha-web/v2/");
    }

    @DisplayName("Validando que não é permitido registrar um valor igual a zero ao produto")
    @Test
    public void testNaoEPermitidoRegistrarComValorIgualAZero(){
        String messagemApresentada = new LoginPage(navegador)
                .informarUsuario("admin")
                .informarSenha("admin")
                .clicarEmEntrar()
                .clicarEmAdicionarProduto()
                .informarNomeProduto("Chocolate")
                .informarValorProduto("000")
                .informarCoresProduto("Preto, Branco")
                .clicarEmSalvarProdutoComErro()
                .capurarMensagemApresenta();
        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", messagemApresentada);
    }

    @DisplayName("Validando que não é permitido registrar um valor maior que 7.000,00 reais")
    @Test
    public void testNaoEPermitidoRegistrarComValorMaiorQueSeteMilReais(){
        String messagemApresentada = new LoginPage(navegador)
                .informarUsuario("admin")
                .informarSenha("admin")
                .clicarEmEntrar()
                .clicarEmAdicionarProduto()
                .informarNomeProduto("Chocolate")
                .informarValorProduto("700001")
                .informarCoresProduto("Preto, Branco")
                .clicarEmSalvarProdutoComErro()
                .capurarMensagemApresenta();
        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", messagemApresentada);
    }

    @DisplayName("Validando que é permitido cadastrar um produto com sucesso")
    @Test
    public void testAdicionarProdutoComSucesso(){
        String messagemApresentada = new LoginPage(navegador)
                .informarUsuario("admin")
                .informarSenha("admin")
                .clicarEmEntrar()
                .clicarEmAdicionarProduto()
                .informarNomeProduto("Chocolate")
                .informarValorProduto("100")
                .informarCoresProduto("Preto, Branco")
                .clicarEmSalvarProdutoComSucesso()
                .capurarMensagemApresenta();
        Assertions.assertEquals("Produto adicionado com sucesso", messagemApresentada);
    }

    @DisplayName("Validando que é permitido cadastrar um produto com valor igual a 7.000,00 reais com sucesso")
    @Test
    public void testAdicionarProdutoComValorDeSeteMilReaisComSucesso(){
        String messagemApresentada = new LoginPage(navegador)
                .informarUsuario("admin")
                .informarSenha("admin")
                .clicarEmEntrar()
                .clicarEmAdicionarProduto()
                .informarNomeProduto("Chocolate")
                .informarValorProduto("700000")
                .informarCoresProduto("Preto, Branco")
                .clicarEmSalvarProdutoComSucesso()
                .capurarMensagemApresenta();
        Assertions.assertEquals("Produto adicionado com sucesso", messagemApresentada);
    }

    @AfterEach
    public void afterEach(){
        this.navegador.quit();
    }
}
