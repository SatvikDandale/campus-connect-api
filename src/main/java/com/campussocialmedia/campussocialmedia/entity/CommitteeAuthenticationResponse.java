package com.campussocialmedia.campussocialmedia.entity;

import java.io.Serializable;

public class CommitteeAuthenticationResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private final String jwt;
	private CommitteeAbout committee;
	
	public CommitteeAuthenticationResponse(String jwt, CommitteeAbout committee) {
		super();
		this.jwt = jwt;
		this.committee = committee;
	}

	public CommitteeAuthenticationResponse(String jwt) {
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}
	
	public CommitteeAbout getCommittee() {
		return committee;
	}

	public void setCommittee(CommitteeAbout committee) {
		this.committee = committee;
	}

	@Override
	public String toString() {
		return "CommitteeAuthenticationResponse [jwt=" + jwt + ", committee=" + committee + "]";
	}

	
}
