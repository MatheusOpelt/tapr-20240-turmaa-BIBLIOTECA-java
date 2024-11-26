package br.univille.microservcolegio.secretaria.service.impl;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.microservcolegio.secretaria.entity.Aluno;
import br.univille.microservcolegio.secretaria.repository.AlunoRepository;
import br.univille.microservcolegio.secretaria.service.AlunoService;

@Service
public class AlunoServiceImpl 
    implements AlunoService{

    @Autowired
    private AlunoRepository repository;

    @Override
    public List<Aluno> getAll() {
        var retorno = repository.findAll();
        List<Aluno> listaAlunos = new ArrayList<Aluno>();
        retorno.forEach(listaAlunos::add);

        return listaAlunos;
    }

    @Override
    public Aluno save(Aluno aluno) {
        return repository.save(aluno);
    }

    @Override
    public Aluno update(String id, Aluno aluno) {
        var buscaAlunoAntigo = repository.findById(id);
        if(buscaAlunoAntigo.isPresent()){
            var alunoAntigo = buscaAlunoAntigo.get();
            alunoAntigo.setNome(aluno.getNome());

            repository.save(alunoAntigo);
            return alunoAntigo;
        }
        return null;
    }

    @Override
    public Aluno delete(String id) {
        var buscaAlunoAntigo = repository.findById(id);
        if(buscaAlunoAntigo.isPresent()){
            var alunoAntigo = buscaAlunoAntigo.get();
            repository.delete(alunoAntigo);
            return alunoAntigo;
        }
        return null;

    }
}
