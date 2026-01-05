interface Prototype {
    Prototype clone();
}

class Resume implements Prototype {
    String name;
    String education;
    String experience;
    String skills;

    public Resume (String name, String education, String experience, String skills) {
        this.name = name;
        this.education = education;
        this.experience = experience;
        this.skills = skills;
    }

    @Override
    public Resume clone () {
        return new Resume(this.name, this.education, this.experience, this.skills);
    }

    public void setName (String name) {
        this.name = name;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void show() {
        System.out.println("Name: " + name);
        System.out.println("Education: " + education);
        System.out.println("Experience: " + experience);
        System.out.println("Skills: " + skills);
        System.out.println("------------");
    }
}

class Main {
    public static void main (String[] args) {
        Resume baseResume = new Resume(
            "Default",
            "B.E CSE",
            "Fresher",
            "Java, Linux, AWS"
        );

        Resume khaleelResume = (Resume) baseResume.clone();
        khaleelResume.setName("Khaleel");

        Resume abdulResume = (Resume) baseResume.clone();
        abdulResume.setName("Abdul");

        abdulResume.show();
        khaleelResume.show();
    }
}