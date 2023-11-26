package br.com.tiagoiwamoto.adapter.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OpenProfileResponse {

    private List<CertificationDTO> certifications;
    private List<CourseCategoryDTO> coursesCategories;
    private List<CourseDTO> latestCourses;
    private List<ExperienceDTO> experiences;
    private List<ProfileDTO> profiles;
    private List<ProjectDTO> projects;
    private List<ResumeDTO> resumes;
    private List<ScholarityDTO> scholarities;
    private List<SkillDTO> skills;
    private List<SoftwareDTO> softwares;
    private List<CourseMetricDTO> metrics;
}
