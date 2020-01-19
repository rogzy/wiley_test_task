package web;

import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.List;

@UtilityClass
public class Expected {
    public List<String> firstTaskExpectedList() {
        return Arrays.asList(
                "Students",
                "Instructors",
                "Book Authors",
                "Professionals",
                "Researchers",
                "Institutions",
                "Librarians",
                "Corporations",
                "Societies",
                "Journal Editors",
                "Government");
    }

    public List<String> fourthTaskExpectedList() {
        return Arrays.asList(
                "Assessment, Evaluation Methods",
                "Classroom Management",
                "Conflict Resolution & Mediation",
                "Curriculum Tools",
                "Education & Public Policy",
                "Educational Research",
                "General Education",
                "Higher Education",
                "Information & Library Science",
                "Special Education",
                "Special Topics",
                "Vocational Technology");
    }
}
