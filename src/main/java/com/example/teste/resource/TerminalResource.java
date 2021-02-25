package com.example.teste.resource;

import com.example.teste.domain.Terminal;
import com.example.teste.dtos.TerminalDTO;
import com.example.teste.repository.TerminalRepository;
import com.example.teste.service.TerminalService;
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
@RequestMapping(value = "/")
public class TerminalResource {
    @Autowired
    private TerminalService terminalService;

    @Autowired
    private TerminalRepository terminalRepository;

    @GetMapping("api/v1/terminais")
    @ApiOperation(code = 200, value = "Retorna uma lista com todas as terminals cadastradas.", response = Terminal[].class)
    @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    public ResponseEntity<List<Terminal>> bucarTodos(){
        final List<Terminal> terminals = terminalService.buscarTodos();
        return ResponseEntity.ok().body(terminals);
    }

    @ApiOperation(code = 200, value = "Retorna uma instância de terminal cadastradas a partir do atributo (logic).", response = Terminal[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
            @ApiResponse(code = 404, message = "terminal não não encontrado.")})
    @GetMapping("api/v1/terminal/{logic}")
    public ResponseEntity<Terminal> buscarPorLogic(@PathVariable("logic") Integer logic) throws Exception {

        final Terminal terminal = terminalService.buscarLogic(logic);

        return new ResponseEntity<>(terminal, HttpStatus.OK);
    }

    @PostMapping("/api/v1/terminal")
    @ApiOperation(code = 201, value = "Cria um novo terminal. Ex.: de entrada 44332211;123;PWWIN;0;F04A2E4088B;4;8.00b3;0;16777216;PWWIN")
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
        terminal.setSam(Integer.parseInt(d[3]));
        terminal.setPtid(d[4]);
        terminal.setPlat(Integer.parseInt(d[5]));
        terminal.setVersion(d[6]);
        terminal.setMxr(Integer.parseInt(d[7]));
        terminal.setMxf(Integer.parseInt(d[8]));
        terminal.setVerfm(d[9]);



       final Terminal terminalSalvo = terminalService.salvar(terminal);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{logic}")
                .buildAndExpand(terminal.getLogic()).toUri();
        response.setHeader("Location", uri.toASCIIString());


        return new ResponseEntity<>(terminalSalvo, HttpStatus.CREATED);
    }

    @ApiOperation(code = 204, value = "Atualiza uma terminal a partir do atributo (logic).")
    @ApiResponse(code = 400, message = "Os campos: (logic, serial, model, version) devem ser preenchidos")
    @PutMapping("api/v1/terminal/{logic}")
    public ResponseEntity<Terminal> atualizar(@RequestBody TerminalDTO objDTO, @PathVariable("logic") Integer logic, HttpServletResponse response) throws Exception {
        Terminal obj = terminalService.fromDTO(objDTO);
        obj.setLogic(objDTO.getLogic());
        obj.setSerial(objDTO.getSerial());
        obj.setModel(objDTO.getModel());
        obj.setSam(objDTO.getSam());
        obj.setPtid(objDTO.getPtid());
        obj.setPlat(objDTO.getPlat());
        obj.setVersion(objDTO.getVersion());
        obj.setMxr(objDTO.getMxr());
        obj.setMxf(objDTO.getMxf());
        obj.setVerfm(objDTO.getVerfm());
        obj = terminalService.salvar(obj);

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
