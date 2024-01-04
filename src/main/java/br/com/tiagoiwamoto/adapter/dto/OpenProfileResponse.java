package br.com.tiagoiwamoto.adapter.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class OpenProfileResponse {

    private List<CertificationDTO> certifications;
    private List<CertificationDTO> latestCertifications;
    private List<CourseCategoryDTO> coursesCategories;
    private List<CourseDTO> latestCourses;
    private List<ExperienceDTO> experiences;
    private List<ProfileDTO> profiles;
    private List<ProjectDTO> projects;
    private List<ResumeDTO> resumes;
    private List<ScholarityDTO> scholarities;
    private List<SkillDTO> skills;
    private List<SoftwareDTO> softwares;
    private List<CourseMetricDTO> coursesHoursMetrics;
    private List<CourseMetricDTO> courseTotalMetrics;
}
