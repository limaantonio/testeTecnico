package com.example.teste.service.impl;

import com.example.teste.domain.Terminal;
import com.example.teste.dtos.TerminalDTO;
import com.example.teste.repository.TerminalRepository;
import com.example.teste.service.TerminalService;
import com.example.teste.service.exception.LogicNaoEncontradoException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TerminalServiceImpl implements TerminalService {
    private final TerminalRepository terminalRepository;

    public TerminalServiceImpl(TerminalRepository terminalRepository) {
        this.terminalRepository = terminalRepository;
    }

    @Override
    public List<Terminal> buscarTodos() {
        List<Terminal> optional = terminalRepository.findAll();
        return optional;
    }

    @Override
    public Terminal salvar(Terminal terminal) {
        return terminalRepository.save(terminal);
    }

    @Override
    public Terminal buscarLogic(Integer logic) throws LogicNaoEncontradoException {
        Optional <Terminal> optional = terminalRepository.findByLogic(logic);
        return optional.orElseThrow(() -> new LogicNaoEncontradoException("Não existe terminal com o parametro " +logic));
    }

    @Transactional
    @Override
    public Terminal atualizar(Terminal terminal) {
        Terminal newTerminal = terminalRepository.findByLogic(terminal.getLogic()).get();
        updateData(newTerminal, terminal);
        return terminalRepository.save(newTerminal);
    }

    private void updateData(Terminal newObj, Terminal obj) {
        newObj.setLogic(obj.getLogic());
        newObj.setModel(obj.getModel());
        newObj.setSerial(obj.getSerial());
        newObj.setSam(obj.getSam());
        newObj.setPtid(obj.getPtid());
        newObj.setPlat(obj.getPlat());
        newObj.setVersion(obj.getVersion());
        newObj.setMxr((obj.getMxr()));
        newObj.setMxf(obj.getMxf());
        newObj.setVerfm(obj.getVerfm());


    }

    public Terminal fromDTO(TerminalDTO objDto) {
        return new Terminal(objDto.getId(), objDto.getLogic(), objDto.getSerial(), objDto.getModel());
    }

}
