package com.example.teste.resource;

import com.example.teste.domain.Terminal;
import com.example.teste.dtos.InfoDTO;
import com.example.teste.repository.InfoRepository;
import com.example.teste.service.InfoService;
import com.example.teste.service.exception.LogicNaoEncontradoException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;

@RestController
public class InfoResource {
    @Autowired
    private InfoService infoService;

    @Autowired
    private InfoRepository infoRepository;

    @GetMapping("/api/v1/infos")
    @ApiOperation(code = 200, value = "Retorna uma lista com todas as Infos cadastradas.", response = Terminal[].class)
    @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    public ResponseEntity<List<Terminal>> bucarTodos(){
        final List<Terminal> terminals = infoService.buscarTodos();
        return ResponseEntity.ok().body(terminals);
    }

    @ApiOperation(code = 200, value = "Retorna uma instância de Info cadastradas a partir do atributo (logic).", response = Terminal[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
            @ApiResponse(code = 404, message = "Info não não encontrado.")})
    @GetMapping("api/v1/info/{logic}")
    public ResponseEntity<Terminal> buscarPorLogic(@PathVariable("logic") Integer logic) throws Exception {

        final Terminal terminal = infoService.buscarLogic(logic);

        return new ResponseEntity<>(terminal, HttpStatus.OK);
    }

    @PostMapping("/api/v1/info")
    @ApiOperation(code = 201, value = "Cria uma nova Info.")
    @ApiResponse(code = 400, message = "Os campos: (logic, serial, model, version) devem ser preenchidos")
    public ResponseEntity<Terminal> salvar(@RequestBody String dado, HttpServletResponse response){
        if(dado == null){
            return ResponseEntity.badRequest().build();
        }

        String[] d = dado.split(";");

        Terminal terminal = new Terminal();

        terminal.setLogic(Integer.parseInt(d[0]));
        terminal.setSerial(d[1]);
        terminal.setModel(d[2]);


       final Terminal terminalSalvo = infoService.salvar(terminal);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{logic}")
                .buildAndExpand(terminal.getLogic()).toUri();
        response.setHeader("Location", uri.toASCIIString());


        return new ResponseEntity<>(terminalSalvo, HttpStatus.CREATED);
    }

    @ApiOperation(code = 204, value = "Atualiza uma Info a partir do atributo (logic).")
    @ApiResponse(code = 400, message = "Os campos: (logic, serial, model, version) devem ser preenchidos")
    @PutMapping("api/v1/info/{logic}")
    public ResponseEntity<Terminal> atualizar(@RequestBody InfoDTO objDTO, @PathVariable("logic") Integer logic, HttpServletResponse response) throws Exception {
        Terminal obj = infoService.fromDTO(objDTO);
        obj.setLogic(objDTO.getLogic());
        obj.setSerial(objDTO.getSerial());
        obj.setModel(objDTO.getModel());
        obj = infoService.salvar(obj);

        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler({LogicNaoEncontradoException.class})
    public ResponseEntity<Erro> handleLogicNaoEncontradoException(LogicNaoEncontradoException e){
        return new ResponseEntity<>(new Erro(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    class Erro {
        private String erro;

        public Erro(String erro) {
            this.erro = erro;
        }

        public String getErro() {
            return erro;
        }
    }
}
