abstract class LeaveApprover {
    protected LeaveApprover next;

    public void setNext(LeaveApprover next) {
        this.next = next;
    }

    public abstract void approve(int days);
}

class TeamLead extends LeaveApprover {
    @Override
    public void approve (int days) {
        if (days <= 2) {
            System.out.println("Team Lead approved the leave");
        }
        else if (next != null) {
            next.approve(days);
        }
    }
}

class Manager extends LeaveApprover {
    @Override
    public void approve (int days) {
        if (days <= 5) {
            System.out.println("Manager approved the leave");
        }
        else if (next != null) {
            next.approve(days);
        }
    }
}

class HR extends LeaveApprover {
    @Override
    public void approve (int days) {
        System.out.println("HR approved the leave");
    }
}

public class Main {
    public static void main(String[] args) {
        LeaveApprover teamLead = new TeamLead();
        LeaveApprover manager = new Manager();
        LeaveApprover hr = new HR();

        teamLead.setNext(manager);
        manager.setNext(hr);

        teamLead.approve(1);
        teamLead.approve(4);
        teamLead.approve(20);
    }
}

