package com.example.teste.service;

import com.example.teste.domain.Terminal;
import com.example.teste.dtos.TerminalDTO;
import com.example.teste.service.exception.LogicNaoEncontradoException;

import java.util.List;

public interface TerminalService {

    List<Terminal> buscarTodos();

    Terminal salvar(Terminal terminal);

    Terminal buscarLogic(Integer logic) throws Exception, LogicNaoEncontradoException;

    Terminal fromDTO(TerminalDTO objDTO);

    Terminal atualizar(Terminal obj);
}
