
class BuilderPattern {
    public static void main (String[] args) {
        User u = new User.Builder("Khaleel", "abdkhaleel16@gmail.com")
                            .age(20)
                            .phone("100000")
                            .build();
        System.out.println(u.toString());
    }
}
class User {
    private final String name;
    private final String email;
    private final String address;
    private final String phone;
    private final int age;
    private final boolean newsletter;

    private User(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.address = builder.address;
        this.phone = builder.phone;
        this.age = builder.age;
        this.newsletter = builder.newsletter;
    }

    public static class Builder {
        private final String name;
        private final String email;
        private String address;
        private String phone;
        private int age;
        private boolean newsletter;

        public Builder (String name, String email) {
            this.name = name;
            this.email = email;
        }

        public Builder address (String address) {
            this.address = address;
            return this;
        }

        public Builder phone (String phone) {
            this.phone = phone;
            return this;
        }

        public Builder age (int age) {
            this.age = age;
            return this;
        }

        public Builder newsletter (boolean newsletter) {
            this.newsletter = newsletter;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                ", newsletter=" + newsletter +
                '}';
    }

}