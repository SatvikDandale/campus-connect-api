package com.campussocialmedia.campussocialmedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campussocialmedia.campussocialmedia.entity.ReportDBEntity;
import com.campussocialmedia.campussocialmedia.repository.ReportRepository;
import com.campussocialmedia.campussocialmedia.repository.UserRepository;

@Service
public class ReportService {
	
	@Autowired
	private ReportRepository reportRepository;

	public ReportDBEntity findReportEntityByCollegeName(String collegeName) {
		ReportDBEntity reportDBEntity = reportRepository.findByCollegeName(collegeName);
		return reportDBEntity;
	}

	public void updateReport(ReportDBEntity reportDBEntity) {
		reportRepository.updateReportEntity(reportDBEntity);
	}

	public void addReport(ReportDBEntity reportDBEntity) {
		reportRepository.addReport(reportDBEntity);
	}

}
