package com.campussocialmedia.campussocialmedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.campussocialmedia.campussocialmedia.entity.Post;
import com.campussocialmedia.campussocialmedia.entity.ReportDBEntity;
import com.campussocialmedia.campussocialmedia.repository.ReportRepository;
import com.campussocialmedia.campussocialmedia.repository.UserRepository;

@Service
public class ReportService {
	
	@Autowired
	private ReportRepository reportRepository;

	@Autowired
	private PostService postService;

	public ReportDBEntity findReportEntityByCollegeName(String collegeName) {
		ReportDBEntity reportDBEntity = reportRepository.findByCollegeName(collegeName);
		return reportDBEntity;
	}
	public List<Post> findReportEntityByCollegeNamePosts(String collegeName) {
		ReportDBEntity reportDBEntity = reportRepository.findByCollegeName(collegeName);
		// return getReportedPosts(reportDBEntity.getPostIDs());

		Map<String, Integer> posts = reportDBEntity.getPostIDs();
		List<String> postIds = new ArrayList<String>();
		for(String postId : posts.keySet()) {
			postIds.add(postId);
		}

		return getReportedPosts(postIds);
	}

	public void updateReport(ReportDBEntity reportDBEntity) {
		reportRepository.updateReportEntity(reportDBEntity);
	}

	public void addReport(ReportDBEntity reportDBEntity) {
		reportRepository.addReport(reportDBEntity);
	}

	public List<Post> getReportedPosts(List<String> postIDs) {
		List<Post> posts = new ArrayList<Post>();
		for(String id : postIDs) {
			posts.add(postService.findPostByID(id));
		}
		return posts;
	}

}
