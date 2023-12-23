import java.io.*;
public class  Net_Banking {
    static Account[] aa = new Account[10];
    static int a=1,t=0;
    static Tax[] ll=new Tax[10];
    public static void main(String[] args) throws IOException {
        System.out.println("\t\t\t\t\t*****  WELCOME TO THE NET-BANKING SERVICE OF YES BANK  *****\t\t\t\t\t");
        InputStreamReader read = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(read);
        int ch1;
        aa[0]=new Account();
        aa[0].account_no = 1034554;
        aa[0].name = "Pari Chauhan";
        aa[0].balance = 50000.0;
        aa[0].type = "Savings";
        aa[0].phone_no="9719687566";
        ll[0]=new Tax();
        for(int i=1;i<10;i++) {
            aa[i] = new Account(); //capacity of 10 account
            ll[i] = new Tax();      //capacity of 10 payments
        }
        do {
            System.out.println("\t\t\t\t\tENTER AS INDICATED FOR THE RELATED SERVICES!!!!\t\t\t\t\t");
            System.out.println("1 : ACCOUNTS & SERVICES ");
            System.out.println("2 : ONLINE TAX PAYMENT SERVICES ");
            System.out.println("3 : EXIT ");
            ch1 = Integer.parseInt(in.readLine());
            switch (ch1) {
                case 1:
                    aa[0].choice();
                    break;
                case 2:
                    ll[0].choice1();
                    break;
                case 3:
                    System.out.println("\t\t\t\t\t***** THANK YOU !!!! *****\t\t\t\t\t");
            }
        } while (ch1 != 3);
    }
}
class Account extends Net_Banking {
         long account_no;
         String name, type,phone_no;
        double balance, rate = 8.0;
        void choice()throws IOException
        {
            InputStreamReader read = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(read);
            int ch;
            do {
                System.out.println("\t\t\t\t\t\t\t\tSPECIFY YOUR CHOICE!!!!\t\t\t\t\t");
                System.out.println("1:CREATE ACCOUNT");
                System.out.println("2:CHECK BALANCE IN ACCOUNT");
                System.out.println("3:UPDATE ACCOUNT BALANCE");
                System.out.println("4:WITHDRAW AMOUNT");
                System.out.println("5:DEPOSIT AMOUNT  ");
                System.out.println("6:RETURN TO HOME PAGE");
                  ch = Integer.parseInt(in.readLine());
                  int ob=0;
                    if(ch!=1&&ch!=6) {
                        System.out.println("Please Enter Your 7-digit Account Number");
                        long acn=Long.parseLong(in.readLine());
                        ob=check_account(acn);
                        if (ob==-1) //doesnt exist
                        {
                            System.out.println("No Account with such Number exists!!\nPlease create the Account first..");
                            continue;
                        }
                    }//closing of if as the switch will work in both case whether choice is 1 or not only in the case when c=0 and ch!=1 we continue
                        switch (ch) {
                            case 1:
                                aa[a++].createaccount(); //whenever creating new account that time iterating a
                                break;
                            case 2:
                                aa[ob].checkbalance();
                                break;
                            case 3:
                                aa[ob].updatebalance();
                                break;
                            case 4:
                                System.out.println("Enter the amount to be withdraw: ");
                                double w = Double.parseDouble(in.readLine());
                                if(aa[ob].withdraw(w))
                                    System.out.println();
                                break;
                            case 5:
                                System.out.println("Enter the amount you want to deposit");
                                double de = Double.parseDouble(in.readLine());
                                aa[ob].deposit(de);
                                break;
                            case 6:
                                System.out.println("\t\t\t\t\t_______________________________________________\t\t\t\t\t");
                        }
                    }while(ch!=6);
        }
        int check_account(long ac)
        {
            for (int i = 0; i < 10; i++) {
                if (ac == aa[i].account_no)
                    return  i;
            }
            return -1;
        }
        void createaccount()throws IOException
        {
            InputStreamReader read=new InputStreamReader(System.in);
            BufferedReader in=new BufferedReader(read);
            System.out.println("Enter your name");
            name = in.readLine();
            System.out.println("Enter your Phone Number");
            phone_no=in.readLine();
            System.out.println("Suggest an Account Number of 7-digits");
            long ac = Long.parseLong(in.readLine());
            while(check_account(ac)!=-1)
            {
                System.out.println("Account with such Number Already Exist..");
                System.out.println("Suggest a new 7-digit Account Number");
                ac=Long.parseLong(in.readLine());
            }
            account_no=ac;
            System.out.println("Enter the type of account u want to open:(SAVINGS OR CURRENT)");
            type = in.readLine();
            System.out.println("Enter the balance you want to enter in your new account");
            balance= Double.parseDouble(in.readLine());
            System.out.println("\t\t\t\t\t---------------Account Created With Following Details------------------\t\t\t\t\t");
            System.out.println("Name: " + name);
            System.out.println("Phone Number: "+phone_no);
            System.out.println("Account Number: " + account_no);
            System.out.println("Balance: Rs. " + balance);
            System.out.println("Account Type: " + type);
            System.out.println("\t\t\t\t\t________________________________________________________\t\t\t\t\t");
        }
         void checkbalance() {
            System.out.println("Name of the Account Holder:"+name);
            System.out.println("Current Balance in Your Account: Rs. " + balance);
        }

         void updatebalance() {
            if (type.equalsIgnoreCase("Savings"))
                balance = balance + (balance * rate) / 200.0;
            if (type.equalsIgnoreCase("Current"))
                balance = balance + (balance * rate)/100.0;
            System.out.println("Bank Balance UPDATED Successfully!!");
        }

         boolean withdraw(double wa) {
            if (wa > balance){
                System.out.println("INSUFFICIENT Balance in Account!!!!");
                return false;}
            else {
                balance = balance - wa;
                System.out.println(" Amount of Rs."+wa+" has been DEBITED to your Account!!");
                return true;
            }
        }
         void deposit(double d) {
            balance = balance + d;
            System.out.println("Amount of Rs."+d+"has been CREDITED to your Account!!");
        }
    }//class Account close
class Tax extends Account {
    double income, taxr, taxt;
    // String nm;
     void choice1()throws IOException
     {
         InputStreamReader read = new InputStreamReader(System.in);
         BufferedReader in = new BufferedReader(read);
         int ch2;
         System.out.println("\t\t\t\t\t\t\t***** MAKING TAX-PAY EASY WITH YES BANK SERVICE ******\t\t\t\t\t\t\t\t");
         do
         {
             System.out.println("\t\t\t\t\tSPECIFY YOUR CHOICE\t\t\t\t\t");
             System.out.println("1:TAX PAYMENT");
             System.out.println("2:VIEW HISTORY");
             System.out.println("3:RETURN TO HOME PAGE");
             ch2 = Integer.parseInt(in.readLine());
             switch (ch2) {
                 case 1:
                     ll[t].paytax();
                     break;
                 case 2:
                     history();
                     break;
                 case 3:
                     System.out.println("\t\t\t\t\t_____________________________________________________\t\t\t\t\t");
             }
         }while(ch2!=3);
     }
    void dtax() throws IOException {
        if (income <= 300000) {
            taxr = 0;
            System.out.println("Tax Rate:NIL");
        } else if (income > 300000 && income <= 600000) {
            taxr = 5;
            System.out.println("Tax Rate: " + taxr + "%");
        } else if (income > 600000 && income <= 900000) {
            taxr = 10;
            System.out.println("Tax Rate: " + taxr + "%");
        } else if (income > 900000 && income <= 1200000) {
            taxr = 15;
            System.out.println("Tax Rate: " + taxr + "%");
        } else if (income > 1200000 && income <= 1500000) {
            taxr = 20;
            System.out.println("Tax Rate: " + taxr + "%");
        } else {
            taxr = 30;
            System.out.println("Tax Rate: " + taxr + "%");
        }
    }
    void paytax() throws IOException {
        InputStreamReader read = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(read);
        System.out.println("PLEASE SPECIFY THE DETAILS MENTIONED BELOW");
        System.out.println("Your Full Name ");
        ll[t].name = in.readLine();
        System.out.println("Your Annual Income");
        ll[t].income = Double.parseDouble(in.readLine());
        ll[t].dtax();
        ll[t].taxt = (taxr * income) / 100.0;
        System.out.println("Tax to be Paid : Rs. " + ll[t].taxt);
        int c = 0;
        if (ll[t].taxt != 0) {
            System.out.println("Please Enter Your Account Number to Make the Payment");
            long acct = Long.parseLong(in.readLine());
            System.out.println("Enter your IFSC code");
            String ifsc=in.readLine();
                for (int i=0; i<10; i++) {
                    if (acct == Account.aa[i].account_no) {
                        c = 1;
                        if(aa[i].withdraw(taxt))
                            System.out.println("\t\t\t\t\t\t\t TAX PAYMENT SUCCESSFUL!!!! \t\t\t\t\t\t\t");
                        else
                            System.out.println("\t\t\t\t\t\t\t TAX PAYMENT UNSUCCESSFUL!!!! \t\t\t\t\t\t\t");
                        break;
                    }
                }
            if(c==0){
                System.out.println("\t\t\t\t\t\t\t TAX PAYMENT UNSUCCESSFUL!!!! \t\t\t\t\t\t\t");
                System.out.println("\t\t\t\t\tNo Account With Such Details Found in our Bank\t\t\t\t\t");
            }
        }
        t++;
    }
    void history()throws IOException
    {
        InputStreamReader read = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(read);
        System.out.println("Enter the Name associated with YES BANK");
        String s=in.readLine();
        System.out.println("Enter your Account Number");
        long l=Long.parseLong(in.readLine());
        int c=0;
        if(check_account(l)==-1)//doesnt exist
        {
            System.out.println("No Account with such Details Found in our Bank!!!!");
            return;
        }
        for(int i=0;i<10;i++)
        {
            if(s.equalsIgnoreCase(ll[i].name))
            {
                c=1;
                System.out.println("Name of the person: "+ll[i].name);
                System.out.println("Total Income of the person: Rs. "+ll[i].income);
                System.out.println("Total Tax paid by the person: Rs.  "+ll[i].taxt);
                break;
            }
        }
        if(c==0)
            System.out.println("No Previous History associated with this Account Found!!!!");
    }
}
//extended this tax class from account class just to have the access of various accounts
//need to use try catch for null pointer exception in last function bcoz memory is not initiallized to array objects while chosing case 2 then either you need to assign memory to it in this
//or simply use can use catch try block in which it will print in the catch block no accont exist if tax payment is the first choice over account services
//we were allocating new memory every time we were making choice
