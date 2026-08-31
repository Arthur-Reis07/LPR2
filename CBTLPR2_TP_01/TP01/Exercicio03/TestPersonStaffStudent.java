public class TestPersonStaffStudent {
    public static void main(String[] args) {
        System.out.println("===== TESTE DE PERSON =====");
        Person person = new Person("Pessoa 01", "Rua A, 100");
        System.out.println("Nome: " + person.getName());
        System.out.println("Endereço: " + person.getAddress());
        person.setAddress("Rua B, 200");
        System.out.println("Endereço atualizado: " + person.getAddress());
        System.out.println("toString: " + person);

        System.out.println("\n===== TESTE DE STUDENT =====");
        Student student = new Student(
                "Aluno 01",
                "Rua dos Alunos, 10",
                "ADS",
                2026,
                850.50
        );
        System.out.println("Nome herdado: " + student.getName());
        System.out.println("Endereço herdado: " + student.getAddress());
        System.out.println("Programa: " + student.getProgram());
        System.out.println("Ano: " + student.getYear());
        System.out.println("Taxa: " + student.getFee());

        student.setAddress("Rua Nova, 20");
        student.setProgram("Sistemas de Informação");
        student.setYear(2027);
        student.setFee(950.75);

        System.out.println("Após alterações: " + student);

        System.out.println("\n===== TESTE DE STAFF =====");
        Staff staff = new Staff(
                "Professor 01",
                "Avenida Central, 50",
                "IFSP Campus Cubatão",
                4500.00
        );
        System.out.println("Nome herdado: " + staff.getName());
        System.out.println("Endereço herdado: " + staff.getAddress());
        System.out.println("Escola: " + staff.getSchool());
        System.out.println("Pagamento: " + staff.getPay());

        staff.setAddress("Avenida Nova, 60");
        staff.setSchool("IFSP Campus São Paulo");
        staff.setPay(5000.00);

        System.out.println("Após alterações: " + staff);
    }
}
