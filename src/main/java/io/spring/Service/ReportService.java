package io.spring.Service;

import io.spring.Entity.Report;
import io.spring.Repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ReportService {
    @Autowired
    ReportRepository reportRepository;

    public List<Report> fetchAll(){
        return reportRepository.findAll();
    }

    public void save(Report report){
        reportRepository.save(report);
    }

    public Report findById(long id){
        return reportRepository.findById(id).get();
    }

    public void delete(long id){
        reportRepository.deleteById(id);
    }
}
