import java.util.*;
import java.util.Collection;
import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;
public class 炸鸡店 {
        public static void main(String[] args) {
            Scanner scanner=new Scanner(System .in);
            West2FriedChickenRestauran west2FriedChickenRestauran=new West2FriedChickenRestauran();
            west2FriedChickenRestauran.Sale() ;//开始营业(销售,过期,进货,异常)
        }
    }
    abstract class Drinks{
                protected String name;//饮料名字
                protected double cost;//价格
                protected LocalDate date;//生产日期
                protected int exp;//保质期
                public Drinks(String name,double cost,int y,int m,int d){
                    this.name=name;
                    this.cost=cost;
//                  this.date=new LocalDate(y,m,d);
                }

                 public String getName() {
                        return name;
                    }

                 public double getCost() {
                        return cost;
                    }

                 public int getExp() {
                        return exp;
                    }

                 public LocalDate getDate() {
                        return date;
                    }

    }

    class Beer extends Drinks{
        float level=8;
        protected int exp=30;
        public Beer(String name,double cost,int y,int m,int d){
        super(name,cost,y,m,d);
    }
        @Override
            public String toString(){
              return this.name+"\t"+this.cost;
    }
    }

    class Juice extends Drinks{
        protected int exp=2;
        public Juice(String name,double cost,int y,int m,int d){
            super(name,cost,y,m,d);
        }
        @Override
            public String toString(){
            return this.name+"\t"+this.cost;
        }
        }


    class setMeal{
        String mealname;
        double price;
        String chickenname;
        String drinkname;
        int number=10;
        public setMeal (String Mealname,double price,String Chickenname,String drinkname){
        this.mealname =Mealname ;
        this.price =price;
        this.chickenname =Chickenname;
        this.drinkname =drinkname ;

    }
        public String toString(){
        return this.mealname ;
    }
    }

    class IngredientSortOutException  extends RuntimeException{
        String message;
        public IngredientSortOutException() {
            super();
        }
        public IngredientSortOutException(String message, Throwable cause) {
            super(message, cause);
            this.message=message;
        }
        public IngredientSortOutException(String message) {
            super(message);
            this.message=message;
        }
        public IngredientSortOutException(Throwable cause) {
            super(cause);
            this.message=message;
        }
        public String GetMessage(){
            return message;
        }
    }

    class OverdraftBalanceException  extends RuntimeException{
        String message;
        public OverdraftBalanceException() {
            super();
        }
        public OverdraftBalanceException(String message, Throwable cause) {
            super(message, cause);
            this.message=message;
        }
        public OverdraftBalanceException(String message) {
            super(message);
            this.message=message;
        }
        public OverdraftBalanceException(Throwable cause) {
            super(cause);
            this.message=message;
        }
        public String GetMessage(){
            return message;
        }
    }

    interface FriedChickenRestaurant{
        void Sale();
        void Purchase();
    }

    class West2FriedChickenRestauran implements FriedChickenRestaurant{
        double vacancies=50000000;//余额
        int isjinhuo=0;//1:需要进货 0:无需进货
        static ArrayList <Beer> Beerlist=new ArrayList();
        static ArrayList <Juice > Juicelist=new ArrayList<>() ;
        static ArrayList <setMeal>Meallist=new ArrayList();
        West2FriedChickenRestauran(){ }
        static {
            for(int i=1;i<=10;i++){
                Beerlist.add(new Beer("雪津",12.5,2020,12,11)) ;
                Beerlist.add(new Beer("科罗娜",30,2020,12,12)) ;
                Juicelist.add(new Juice("橙汁",15,2020,12,12));
                Juicelist.add(new Juice("椰汁",25,2020,12,12));
                Meallist .add(new setMeal("快乐每一天套餐",48,"麦辣大鸡","橙汁"));
                Meallist .add(new setMeal("开心每一天套餐",58,"超辣辣辣气泡脆皮大鸡","雪津"));
                Meallist .add(new setMeal("奥利给每一天套餐",68,"香辣超大鸡","椰汁"));
                Meallist .add(new setMeal("冲!每一天套餐",78,"麦辣超大鸡","科罗娜"));

            }
        }
        @Override
        public void Sale() {
            isjinhuo =0;
            Scanner scanner=new Scanner(System .in);
            int flag1,flag2,flag3;
            flag1=scanner.nextInt();
            flag2=scanner.nextInt();
            flag3=scanner.nextInt();
            if(flag1==1)
                Use(new Beer("雪津",12.5,2020,12,11));
            else if(flag1==2)
                Use(new Beer("科罗娜",30,2020,12,12));
            if(flag2==1)
                Use(new Juice("橙汁",15,2020,12,12));
            else if(flag2==2)
                Use(new Juice("椰汁",25,2020,12,12));
            if(flag3==1)
                Use(new setMeal("快乐每一天套餐",48,"麦辣大鸡","橙汁"));
            else if(flag3==2)
                Use(new setMeal("开心每一天套餐",58,"超辣辣辣气泡脆皮大鸡","雪津"));
            else if(flag3==3)
                Use(new setMeal("奥利给每一天套餐",68,"香辣超大鸡","椰汁"));
            else if(flag3==4)
                Use(new setMeal("冲!每一天套餐",78,"麦辣超大鸡","科罗娜"));
            if(isjinhuo ==1)
                Purchase() ;
        }

        @Override
        public void Purchase() {
                if(!Beerlist.contains(new Beer("雪津",12.5,2020,12,11))){
                    for(int i=1;i<=10;i++)
                        Beerlist .add(new Beer("雪津",12.5,2020,12,11));
                    try{
                        vacancies-=500;
                        if(vacancies<=0)
                            throw new OverdraftBalanceException();
                    }
                    catch (OverdraftBalanceException e){
                        System.out .println("余额不足且正在赊账购买雪津");
                    }
                }
            if(!Beerlist.contains(new Beer("科罗娜",30,2020,12,12))){
                for(int i=1;i<=10;i++)
                    Beerlist .add(new Beer("科罗娜",30,2020,12,12));
                try{
                    vacancies-=700;
                    if(vacancies<=0)
                        throw new OverdraftBalanceException();
                }
                catch (OverdraftBalanceException e){
                    System.out .println("余额不足且正在赊账购买科罗娜");
                }
            }
            if(!Juicelist .contains(new Juice("橙汁",15,2020,12,12)) ){
                for(int i=1;i<=10;i++)
                    Juicelist .add(new Juice("橙汁",15,2020,12,12));
                try{
                    vacancies-=600;
                    if(vacancies<=0)
                        throw new OverdraftBalanceException();
                }
                catch (OverdraftBalanceException e){
                    System.out .println("余额不足且正在赊账购买橙汁");
                }
            }
            if(!Juicelist .contains(new Juice("椰汁",25,2020,12,12)) ){
                for(int i=1;i<=10;i++)
                    Juicelist .add(new Juice("椰汁",25,2020,12,12) );
                try{
                    vacancies-=700;
                    if(vacancies<=0)
                        throw new OverdraftBalanceException();
                }
                catch (OverdraftBalanceException e){
                    System.out .println("余额不足且正在赊账购买椰汁");
                }
            }
            if(!Meallist  .contains(new setMeal("快乐每一天套餐",48,"麦辣大鸡","橙汁") ) ){
                for(int i=1;i<=10;i++)
                    Meallist .add(new setMeal("快乐每一天套餐",48,"麦辣大鸡","橙汁"));
                try{
                    vacancies-=700;
                    if(vacancies<=0)
                        throw new OverdraftBalanceException();
                }
                catch (OverdraftBalanceException e){
                    System.out .println("余额不足且正在赊账购买快乐每一天套餐");
                }
            }

            if(!Meallist  .contains(new setMeal("开心每一天套餐",58,"超辣辣辣气泡脆皮大鸡","雪津") ) ){
                for(int i=1;i<=10;i++)
                    Meallist .add(new setMeal("开心每一天套餐",58,"超辣辣辣气泡脆皮大鸡","雪津"));
                try{
                    vacancies-=700;
                    if(vacancies<=0)
                        throw new OverdraftBalanceException();
                }
                catch (OverdraftBalanceException e){
                    System.out .println("余额不足且正在赊账购买开心每一天套餐");
                }
            }
            if(!Meallist  .contains(new setMeal("奥利给每一天套餐",68,"香辣超大鸡","椰汁") ) ){
                for(int i=1;i<=10;i++)
                    Meallist .add(new setMeal("奥利给每一天套餐",68,"香辣超大鸡","椰汁"));
                try{
                    vacancies-=700;
                    if(vacancies<=0)
                        throw new OverdraftBalanceException();
                }
                catch (OverdraftBalanceException e){
                    System.out .println("余额不足且正在赊账购买奥利给每一天套餐");
                }
            }
            if(!Meallist  .contains(new setMeal("冲!每一天套餐",78,"麦辣超大鸡","科罗娜") ) ){
                for(int i=1;i<=10;i++)
                    Meallist .add(new setMeal("冲!每一天套餐",78,"麦辣超大鸡","科罗娜"));
                try{
                    vacancies-=700;
                    if(vacancies<=0)
                        throw new OverdraftBalanceException();
                }
                catch (OverdraftBalanceException e){
                    System.out .println("余额不足且正在赊账购买冲!每一天套餐");
                }
            }
        }
        public void Use(Beer b){
                try{
                    if(Beerlist.contains(b )){
                        LocalDate date1=LocalDate.now() ;//当前日期
                        LocalDate date2=b.getDate() .plusDays(30); //啤酒的ddl
                    if(date2.isBefore(date1) ){
                        while(Beerlist .indexOf(b) !=-1)//删除全部啤酒
                            Beerlist .remove (Beerlist .indexOf(b) );
                        throw new IngredientSortOutException();
                    }
                    else{
                        Beerlist .remove(Beerlist .indexOf(b) );
                        vacancies +=b.getCost() ;
                        if(Beerlist .indexOf(b) ==-1)
                            throw new IngredientSortOutException();
                    }

                }
                    else {
                        throw new IngredientSortOutException();
                    }
                }
                catch(IngredientSortOutException e){
                    System.out.println("Beers are running out");
                    isjinhuo=1;
                }
        }
        public void Use(Juice j){
            try{
                if(Juicelist.contains(j )){//要放在前面
                    LocalDate date1=LocalDate.now() ;//当前日期
                    LocalDate date2=j.getDate() .plusDays(30); //啤酒的ddl
                    if(date2.isBefore(date1) ){//删除全部啤酒
                        while(Juicelist  .indexOf(j) !=-1)
                            Juicelist  .remove (Juicelist  .indexOf(j) );

                        throw new IngredientSortOutException();

                    }
                    else{
                        Juicelist  .remove(Juicelist  .indexOf(j) );
                        vacancies +=j.getCost() ;
                        if(Juicelist  .indexOf(j) ==-1)
                            throw new IngredientSortOutException();
                    }

                }
                else {
                    throw new IngredientSortOutException();
                }
            }
            catch(IngredientSortOutException e){
                System.out.println("Juices are running out");
                isjinhuo=1;
            }
        }
        public void Use(setMeal s){
            try{
                if(Meallist .contains(s) ){
                    Meallist .remove(Meallist .indexOf(s) );
                    vacancies +=s.price ;
                }
                else
                    throw new IngredientSortOutException();
            }
            catch(IngredientSortOutException e){
                System.out.println("Meals are running out");
                isjinhuo=1;
            }
        }
}





// class ControlBeer{
//    Collection <Beer> beers= new LinkedList<>();
//    public void addBeer( Beer beer){
//        beers.add(beer);
//    }
//    public void addNumberOfBeer(Beer beer,int number){
//        boolean flag=false;
//        for(Beer beer2 :beers){
//            if(beer2.getName().equals(beer.getName())){
//                  flag=true;
//                  break;
//            }
//
//        }
//        if(flag){
//            int totalNumber=0;
//            totalNumber=beer.getNumber();
//            totalNumber+=number;
//            beer.setNumber(totalNumber);
//        }else{
//            System.out.println("查无此酒");
//        }
//    }
//}
//
//class ControlJuice{
//    Collection <Juice> juice= new LinkedList<>();
//    public void addJuice( Juice juice){
//        juice.add(juice);
//    }
//    public void addNumberOfJuice(Juice juice,int number){
//        boolean flag=false;
//        for(Juice juice2 :juice){
//            if(juice2.getName().equals(juice.getName())){
//                flag=true;
//                break;
//            }
//
//        }
//        if(flag){
//            int totalNumber=0;
//            totalNumber=juice.getNumber();
//            totalNumber+=number;
//            juice.setNumber(totalNumber);
//        }else{
//            System.out.println("查无此饮料");
//        }
//    }
