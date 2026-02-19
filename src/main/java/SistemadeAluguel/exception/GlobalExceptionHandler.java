package SistemadeAluguel.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler{


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErroResposta> tratarErroDeNegocio(RuntimeException ex) {
        ErroResposta erro = new ErroResposta(400, ex.getMessage());
        return ResponseEntity.status(400).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResposta> tratarErroValidacao(MethodArgumentNotValidException ex) {
        String msg = ex.getBindingResult().getFieldError().getDefaultMessage();
        ErroResposta erro = new ErroResposta(400, msg);
        return ResponseEntity.status(400).body(erro);
    }

    @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
    public ResponseEntity<ErroResposta> tratarErroDuplicidade(org.springframework.dao.DataIntegrityViolationException ex) {
        ErroResposta erro = new ErroResposta(400, "Não foi possível concluir o cadastro. Verifique os dados ou entre em contato com o suporte.");
        return ResponseEntity.status(400).body(erro);
    }

    @ExceptionHandler
    public ResponseEntity<ErroResposta> tratarErroValidacaoBean(jakarta.validation.ConstraintViolationException ex){
        String mensagem = ex.getConstraintViolations().iterator().next().getMessage();
        ErroResposta erro = new ErroResposta(400, mensagem);
        return ResponseEntity.status(400).body(erro);
    }

}
