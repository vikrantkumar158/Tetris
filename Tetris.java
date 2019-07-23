import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
class Node
{
    char ch;
    int[] a1=new int[0],a2=new int[0],pos=new int[0],line=new int[0];
    Node link;
};
class Stack
{
    int count;
    Node head;
    Stack()
    {
        count=0;
        head=null;
    }
};
class Box
{
    public char arr[][]=new char[10][10];
    Box()
    {
        for(int i=0;i<10;++i)
            for(int j=0;j<10;++j)
                arr[i][j]=' ';
    }
    void checkAndDelete(int min,int max,Stack s)
    {
        int i,j,flag=0,k=0;
        for(i=max;i>=min;--i)
        {
            if(String.valueOf(arr[i]).equals("##########"))
            {
                arr[i]=new char[]{' ',' ',' ',' ',' ',' ',' ',' ',' ',' '};
                for(j=i;j>0;--j)
                {
                    arr[j]=Arrays.copyOf(arr[j-1],10);
                    if(String.valueOf(arr[i]).equals("         "))
                        break;
                }
                if(s.head.line.length==0)
                {
                    s.head.line=new int[1];
                    s.head.line[k++]=i-flag;
                }
                else
                {
                    s.head.line=Arrays.copyOf(s.head.line,s.head.line.length+1);
                    s.head.line[k++]=i-flag;
                }
                arr[0]=new char[]{' ',' ',' ',' ',' ',' ',' ',' ',' ',' '};
                ++i;
                ++flag;
                --min;
            }
        }
    }
}
abstract class Shape
{
    public int[] x=new int[4],y=new int[4];
    abstract void clockwiseRotate(Box b);
    abstract void antiClockwiseRotate(Box b);
    Boolean leftShift(Box b)
    {
        if(y[0]+1<=9&&y[1]+1<=9&&y[2]+1<=9&&y[3]+1<=9)
        {
            if(b.arr[x[0]][y[0]+1]==' '&&b.arr[x[1]][y[1]+1]==' '&&b.arr[x[2]][y[2]+1]==' '&&b.arr[x[3]][y[3]+1]==' ')
            {
                y[0]+=1;
                y[1]+=1;
                y[2]+=1;
                y[3]+=1;
                return true;
            }
        }
        return false;
    }
    Boolean rightShift(Box b)
    {
        if(y[0]-1>=0&&y[1]-1>=0&&y[2]-1>=0&&y[3]-1>=0)
        {
            if(b.arr[x[0]][y[0]-1]==' '&&b.arr[x[1]][y[1]-1]==' '&&b.arr[x[2]][y[2]-1]==' '&&b.arr[x[3]][y[3]-1]==' ')
            {
                y[0]-=1;
                y[1]-=1;
                y[2]-=1;
                y[3]-=1;
                return true;
            }
        }
        return false;
    }
    Boolean shiftDown(Box b)
    {
        if(x[0]+1<=9&&x[1]+1<=9&&x[2]+1<=9&&x[3]+1<=9)
        {
            if(b.arr[x[0]+1][y[0]]==' '&&b.arr[x[1]+1][y[1]]==' '&&b.arr[x[2]+1][y[2]]==' '&&b.arr[x[3]+1][y[3]]==' ')
            {
                x[0]+=1;
                x[1]+=1;
                x[2]+=1;
                x[3]+=1;
                return true;
            }
        }
        return false;
    }
    Boolean shiftUp(Box b)
    {
        if(x[0]-1>=0&&x[1]-1>=0&&x[2]-1>=0&&x[3]-1>=0)
        {
            x[0]-=1;
            x[1]-=1;
            x[2]-=1;
            x[3]-=1;
            return true;
        }
        return false;
    }
    Boolean boundaryReached(Box b)
    {
        for(int i=0;i<4;++i)
            b.arr[x[i]][y[i]]=' ';
        if(x[0]==9||x[1]==9||x[2]==9||x[3]==9)
            return true;
        else if(b.arr[x[0]+1][y[0]]=='#'||b.arr[x[1]+1][y[1]]=='#'||b.arr[x[2]+1][y[2]]=='#'||b.arr[x[3]+1][y[3]]=='#')
            return true;
        return false;
    }
}
class Shape1 extends Shape
{
    Shape1()
    {
        Random rand=new Random();
        int n=rand.nextInt(10);
        x[0]=0;
        y[0]=n;
        x[1]=1;
        y[1]=n;
        x[2]=2;
        y[2]=n;
        x[3]=3;
        y[3]=n;
    }
    public void clockwiseRotate(Box b)
    {
        if(x[3]-x[0]==3&&y[3]+3<=9)
        {
            if(b.arr[x[3]][y[3]+3]==' '&&b.arr[x[3]][y[3]+2]==' '&&b.arr[x[3]][y[3]+1]==' '&&b.arr[x[3]][y[3]]==' ')
            {   
                x[0]=x[3];
                y[0]=y[3]+3;
                x[1]=x[3];
                y[1]=y[3]+2;
                x[2]=x[3];
                y[2]=y[3]+1;
            }
        }
        else if(y[0]-y[3]==3&&x[3]+3<=9)
        {
            if(b.arr[x[3]+3][y[3]]==' '&&b.arr[x[3]+2][y[3]]==' '&&b.arr[x[3]+1][y[3]]==' '&&b.arr[x[3]][y[3]]==' ')
            {
                x[0]=x[3]+3;
                y[0]=y[3];
                x[1]=x[3]+2;
                y[1]=y[3];
                x[2]=x[3]+1;
                y[2]=y[3];
            }
        }
        else if(x[0]-x[3]==3&&y[3]-3>=0)
        {
            if(b.arr[x[3]][y[3]-3]==' '&&b.arr[x[3]][y[3]-2]==' '&&b.arr[x[3]][y[3]-1]==' '&&b.arr[x[3]][y[3]]==' ')
            {
                x[0]=x[3];
                y[0]=y[3]-3;
                x[1]=x[3];
                y[1]=y[3]-2;
                x[2]=x[3];
                y[2]=y[3]-1;
            }
        }
        else if(y[3]-y[0]==3&&x[3]-3>=0)
        {
            if(b.arr[x[3]-3][y[3]]==' '&&b.arr[x[3]-2][y[3]]==' '&&b.arr[x[3]-1][y[3]]==' '&&b.arr[x[3]][y[3]]==' ')
            {
                x[0]=x[3]-3;
                y[0]=y[3];
                x[1]=x[3]-2;
                y[1]=y[3];
                x[2]=x[3]-1;
                y[2]=y[3];
            }
        }
    }
    public void antiClockwiseRotate(Box b)
    {
        if(x[3]-x[0]==3&&y[3]-3>=0)
        {
            if(b.arr[x[3]][y[3]-3]==' '&&b.arr[x[3]][y[3]-2]==' '&&b.arr[x[3]][y[3]-1]==' '&&b.arr[x[3]][y[3]]==' ')
            {
                x[0]=x[3];
                y[0]=y[3]-3;
                x[1]=x[3];
                y[1]=y[3]-2;
                x[2]=x[3];
                y[2]=y[3]-1;
            }
        }
        else if(y[0]-y[3]==3&&x[3]-3>=0)
        {
            if(b.arr[x[3]-3][y[3]]==' '&&b.arr[x[3]-2][y[3]]==' '&&b.arr[x[3]-1][y[3]]==' '&&b.arr[x[3]][y[3]]==' ')
            {
                x[0]=x[3]-3;
                y[0]=y[3];
                x[1]=x[3]-2;
                y[1]=y[3];
                x[2]=x[3]-1;
                y[2]=y[3];
            }
        }
        else if(x[0]-x[3]==3&&y[3]+3<=9)
        {
            if(b.arr[x[3]][y[3]+3]==' '&&b.arr[x[3]][y[3]+2]==' '&&b.arr[x[3]][y[3]+1]==' '&&b.arr[x[3]][y[3]]==' ')
            { 
                x[0]=x[3];
                y[0]=y[3]+3;
                x[1]=x[3];
                y[1]=y[3]+2;
                x[2]=x[3];
                y[2]=y[3]+1;
            }
        }
        else if(y[3]-y[0]==3&&x[3]+3<=9)
        {
            if(b.arr[x[3]+3][y[3]]==' '&&b.arr[x[3]+2][y[3]]==' '&&b.arr[x[3]+1][y[3]]==' '&&b.arr[x[3]][y[3]]==' ')
            {
                x[0]=x[3]+3;
                y[0]=y[3];
                x[1]=x[3]+2;
                y[1]=y[3];
                x[2]=x[3]+1;
                y[2]=y[3];
            }
        }
    }
}
class Shape2 extends Shape
{
    Shape2()
    {
        Random rand=new Random();
        int n=rand.nextInt(9);
        x[0]=0;
        y[0]=n;
        x[1]=0;
        y[1]=n+1;
        x[2]=1;
        y[2]=n;
        x[3]=1;
        y[3]=n+1;
    }
    public void clockwiseRotate(Box b)
    {
        
    }
    public void antiClockwiseRotate(Box b)
    {
        
    }
}
class Shape3 extends Shape
{
    Shape3()
    {
        Random rand=new Random();
        int n=rand.nextInt(9);
        x[0]=0;
        y[0]=n;
        x[1]=1;
        y[1]=n;
        x[2]=1;
        y[2]=n+1;
        x[3]=2;
        y[3]=n+1;
    }
    public void clockwiseRotate(Box b)
    {
        if(x[3]-x[0]==2&&y[0]+1<=9)
        {
            if(b.arr[x[2]-1][y[2]+1]==' '&&b.arr[x[2]-1][y[2]]==' '&&b.arr[x[2]][y[2]]==' '&&b.arr[x[2]][y[2]-1]==' ')
            {
                x[3]=x[2];
                y[3]=y[2]-1;
                x[1]=x[2]-1;
                y[1]=y[2];
                x[0]=x[2]-1;
                y[0]=y[2]+1;
            }
        }
        else if(y[0]-y[3]==2&&x[2]+1<=9)
        {
            if(b.arr[x[2]-1][y[2]-1]==' '&&b.arr[x[2]][y[2]-1]==' '&&b.arr[x[2]][y[2]]==' '&&b.arr[x[2]+1][y[2]]==' ')
            {
                x[1]=x[2];
                y[1]=y[2]-1;
                x[3]=x[2]+1;
                y[3]=y[2];
                x[0]=x[2]-1;
                y[0]=y[2]-1;
            }
        }
    }
    public void antiClockwiseRotate(Box b)
    {
        if(x[3]-x[0]==2&&y[0]+1<=9)
        {
            if(b.arr[x[2]-1][y[2]+1]==' '&&b.arr[x[2]-1][y[2]]==' '&&b.arr[x[2]][y[2]]==' '&&b.arr[x[2]][y[2]-1]==' ')
            {
                x[3]=x[2];
                y[3]=y[2]-1;
                x[1]=x[2]-1;
                y[1]=y[2];
                x[0]=x[2]-1;
                y[0]=y[2]+1;
            }
        }
        else if(y[0]-y[3]==2&&x[2]+1<=9)
        {
            if(b.arr[x[2]-1][y[2]-1]==' '&&b.arr[x[2]][y[2]-1]==' '&&b.arr[x[2]][y[2]]==' '&&b.arr[x[2]+1][y[2]]==' ')
            {
                x[1]=x[2];
                y[1]=y[2]-1;
                x[3]=x[2]+1;
                y[3]=y[2];
                x[0]=x[2]-1;
                y[0]=y[2]-1;
            }
        }
    }
}
class Shape4 extends Shape
{
    Shape4()
    {
        Random rand=new Random();
        int n=rand.nextInt(9);
        x[0]=0;
        y[0]=n;
        x[1]=1;
        y[1]=n;
        x[2]=2;
        y[2]=n;
        x[3]=2;
        y[3]=n+1;
    }
    public void clockwiseRotate(Box b)
    {
        if(y[2]+1==y[3]&&y[1]-1>=0)
        {
            if(b.arr[x[1]][y[1]+1]==' '&&b.arr[x[1]][y[1]]==' '&&b.arr[x[1]][y[1]-1]==' '&&b.arr[x[1]+1][y[1]-1]==' ')
            {
                x[0]=x[1];
                y[0]=y[1]+1;
                x[2]=x[1];
                y[2]=y[1]-1;
                x[3]=x[1]+1;
                y[3]=y[1]-1;
            }
        }
        else if(x[2]+1==x[3]&&x[1]-1>=0)
        {
            if(b.arr[x[1]+1][y[1]]==' '&&b.arr[x[1]][y[1]]==' '&&b.arr[x[1]-1][y[1]]==' '&&b.arr[x[1]-1][y[1]-1]==' ')
            {
                x[3]=x[1]-1;
                y[3]=y[1]-1;
                x[2]=x[1]-1;
                y[2]=y[1];
                x[0]=x[1]+1;
                y[0]=y[1];
            }
        }
        else if(y[2]-1==y[3]&&y[1]+1<=9)
        {
            if(b.arr[x[1]][y[1]-1]==' '&&b.arr[x[1]][y[1]]==' '&&b.arr[x[1]][y[1]+1]==' '&&b.arr[x[1]-1][y[1]+1]==' ')
            {
                x[2]=x[1];
                y[2]=y[1]+1;
                x[3]=x[1]-1;
                y[3]=y[1]+1;
                x[0]=x[1];
                y[0]=y[1]-1;
            }
        }
        else if(x[2]-1==x[3]&&x[1]+1<=9)
        {
            if(b.arr[x[1]-1][y[1]]==' '&&b.arr[x[1]][y[1]]==' '&&b.arr[x[1]+1][y[1]]==' '&&b.arr[x[1]+1][y[1]+1]==' ')
            {
                x[0]=x[1]-1;
                y[0]=y[1];
                x[2]=x[1]+1;
                y[2]=y[1];
                x[3]=x[1]+1;
                y[3]=y[1]+1;
            }
        }
    }
    public void antiClockwiseRotate(Box b)
    {
        if(y[2]+1==y[3]&&y[1]-1>=0)
        {
            if(b.arr[x[1]][y[1]-1]==' '&&b.arr[x[1]][y[1]]==' '&&b.arr[x[1]][y[1]+1]==' '&&b.arr[x[1]-1][y[1]+1]==' ')
            {
                x[2]=x[1];
                y[2]=y[1]+1;
                x[3]=x[1]-1;
                y[3]=y[1]+1;
                x[0]=x[1];
                y[0]=y[1]-1;
            }
        }
        else if(x[2]+1==x[3]&&x[1]-1>=0)
        {
            if(b.arr[x[1]-1][y[1]]==' '&&b.arr[x[1]][y[1]]==' '&&b.arr[x[1]+1][y[1]]==' '&&b.arr[x[1]+1][y[1]+1]==' ')
            {
                x[0]=x[1]-1;
                y[0]=y[1];
                x[2]=x[1]+1;
                y[2]=y[1];
                x[3]=x[1]+1;
                y[3]=y[1]+1;
            }
        }
        else if(y[2]-1==y[3]&&y[1]+1<=9)
        {
            if(b.arr[x[1]][y[1]+1]==' '&&b.arr[x[1]][y[1]]==' '&&b.arr[x[1]][y[1]-1]==' '&&b.arr[x[1]+1][y[1]-1]==' ')
            {
                x[0]=x[1];
                y[0]=y[1]+1;
                x[2]=x[1];
                y[2]=y[1]-1;
                x[3]=x[1]+1;
                y[3]=y[1]-1;
            }
        }
        else if(x[2]-1==x[3]&&x[1]+1<=9)
        {
            if(b.arr[x[1]+1][y[1]]==' '&&b.arr[x[1]][y[1]]==' '&&b.arr[x[1]-1][y[1]]==' '&&b.arr[x[1]-1][y[1]-1]==' ')
            {
                x[2]=x[1]-1;
                y[2]=y[1];
                x[3]=x[1]-1;
                y[3]=y[1]-1;
                x[0]=x[1]+1;
                y[0]=y[1];
            }
        }
    }
}
class Shape5 extends Shape
{
    Shape5()
    {
        Random rand=new Random();
        int n=rand.nextInt(8);
        x[0]=0;
        y[0]=n;
        x[1]=0;
        y[1]=n+1;
        x[2]=0;
        y[2]=n+2;
        x[3]=1;
        y[3]=n+1;
    }
    public void clockwiseRotate(Box b)
    {
        if(y[2]-y[0]==2&&x[1]-1>=0)
        {
            if(b.arr[x[0]][y[0]]==' '&&b.arr[x[1]][y[1]]==' '&&b.arr[x[1]-1][y[1]]==' '&&b.arr[x[3]][y[3]]==' ')
            {
                x[2]=x[1]-1;
                y[2]=y[1];
            }
        }
        else if(x[0]-x[2]==1&&y[1]+1<=9)
        {
            if(b.arr[x[1]][y[1]+1]==' '&&b.arr[x[1]][y[1]]==' '&&b.arr[x[1]][y[1]-1]==' '&&b.arr[x[1]-1][y[1]]==' ')
            {
                x[3]=x[1]-1;
                y[3]=y[1];
                x[2]=x[1];
                y[2]=y[1]-1;
                x[0]=x[1];
                y[0]=y[1]+1;
            }
        }
        else if(y[0]-y[2]==2&&x[1]+1<=9)
        {
            if(b.arr[x[1]-1][y[1]]==' '&&b.arr[x[1]][y[1]]==' '&&b.arr[x[1]][y[1]+1]==' '&&b.arr[x[1]+1][y[1]]==' ')
            {
                x[0]=x[1]-1;
                y[0]=y[1];
                x[2]=x[1];
                y[2]=y[1]+1;
                x[3]=x[1]+1;
                y[3]=y[1];
            }
        }
        else if(x[2]-x[0]==1&&y[1]-1>=0)
        {
            if(b.arr[x[1]][y[1]-1]==' '&&b.arr[x[1]][y[1]]==' '&&b.arr[x[2]][y[2]]==' '&&b.arr[x[3]][y[3]]==' ')
            {
                x[0]=x[1];
                y[0]=y[1]-1;
            }
        }
    }
    public void antiClockwiseRotate(Box b)
    {
        if(y[2]-y[0]==2&&x[1]-1>=0)
        {
            if(b.arr[x[1]-1][y[1]]==' '&&b.arr[x[1]][y[1]]==' '&&b.arr[x[2]][y[2]]==' '&&b.arr[x[3]][y[3]]==' ')
            {
                x[0]=x[1]-1;
                y[0]=y[1];
            }
        }
        else if(x[0]-x[2]==1&&y[1]+1<=9)
        {
            if(b.arr[x[0]][y[0]]==' '&&b.arr[x[1]][y[1]]==' '&&b.arr[x[1]][y[1]+1]==' '&&b.arr[x[3]][y[3]]==' ')
            {
                x[2]=x[1];
                y[2]=y[1]+1;
            }
        }
        else if(y[0]-y[2]==2&&x[1]+1<=9)
        {
            if(b.arr[x[1]][y[1]-1]==' '&&b.arr[x[1]][y[1]]==' '&&b.arr[x[1]-1][y[1]]==' '&&b.arr[x[1]+1][y[1]]==' ')
            {
                x[0]=x[1];
                y[0]=y[1]-1;
                x[2]=x[1]-1;
                y[2]=y[1];
                x[3]=x[1]+1;
                y[3]=y[1];
            }
        }
        else if(x[2]-x[0]==1&&y[1]-1>=0)
        {
            if(b.arr[x[1]][y[1]+1]==' '&&b.arr[x[1]][y[1]]==' '&&b.arr[x[1]][y[1]-1]==' '&&b.arr[x[1]-1][y[1]]==' ')
            {
                x[0]=x[1];
                y[0]=y[1]+1;
                x[2]=x[1];
                y[2]=y[1]-1;
                x[3]=x[1]-1;
                y[3]=y[1];
            }
        }
    }
}
public class Tetris
{
    static char top(Stack s)
    {
        return s.head.ch;
    }
    static void push(char c,int l,int x[],int y[],Stack s)
    {
        Node n=new Node();
        n.ch=c;
        n.a1=new int[4];
        n.a2=new int[4];
        n.pos=new int[1];
        n.a1=x;
        n.a2=y;
        n.pos[0]=l;
        n.link=s.head;
        s.head=n;
        s.count+=1;
    }
    static void push(char c,Stack s)
    {
        Node n=new Node();
        n.ch=c;
        n.link=s.head;
        s.head=n;
        s.count+=1;
    }
    static void pop(Stack s)
    {
        s.head=s.head.link;
        s.count-=1;
    }
    static void clear(Stack s)
    {
        s.count=0;
        s.head=null;
    }
    public static void main(String args[])
    {
        System.out.print("\033\143");
        Stack st=new Stack();
        Stack st1=new Stack();
        Random rand=new Random();
        Scanner sc=new Scanner(System.in);
        int i,j,n;
        char ch;
        Shape s;
        n=rand.nextInt(10000000)%5;
        n=1;
        if(n==0)
            s=new Shape1();
        else if(n==1)
            s=new Shape2();
        else if(n==2)
            s=new Shape3();
        else if(n==3)
            s=new Shape4();
        else
            s=new Shape5();
        Box b=new Box();
        for(i=0;i<4;++i)
        {
            b.arr[s.x[i]][s.y[i]]='#';
        }
        for(i=0;i<10;++i)
        {
            for(j=0;j<10;++j)
            {
                System.out.print(b.arr[i][j]+" ");
            }
            System.out.println();
        }
        Boolean flag=true;
        while(true)
        {
            System.out.print("Press to change shape: ");
            ch=sc.next().charAt(0);
            if(ch=='r')
            {
                if(st1.count!=0)
                {
                    if(st1.head.a1.length==0&&st1.head.a2.length==0)
                    {
                        ch=top(st1);
                        pop(st1);
                    }
                    else
                    {
                        ch=top(st1);
                        n=st1.head.pos[0];
                        if(n==0)
                            s=new Shape1();
                        else if(n==1)
                            s=new Shape2();
                        else if(n==2)
                            s=new Shape3();
                        else if(n==3)
                            s=new Shape4();
                        else
                            s=new Shape5();
                        for(int u=0;u<4;++u)
                        {
                            s.x[u]=st1.head.a1[u];
                            s.y[u]=st1.head.a2[u];
                        }
                        pop(st1);
                    }
                }
            }
            if(b.arr[s.x[0]][s.y[0]]==' '||b.arr[s.x[1]][s.y[1]]==' '||b.arr[s.x[2]][s.y[2]]==' '||b.arr[s.x[3]][s.y[3]]==' ')
                flag=false;
            else
                flag=true;
            for(i=0;i<4&&flag;++i)
            {
                b.arr[s.x[i]][s.y[i]]=' ';
            }
            if(ch=='z')
            {
                if(st.count!=0)
                {
                    if(st.head.line.length!=0)
                    {
                        System.out.println("101");
                        int fag=st.head.line.length;
                        for(int u=0;u<10;++u)
                        {
                            if(fag>0&&u==st.head.line[fag-1])
                            {
                                b.arr[u]=new char[]{'#','#','#','#','#','#','#','#','#','#'};
                                --fag;
                            }
                            else
                            {
                                b.arr[u]=b.arr[u+fag];      
                            }
                        }
                    }
                    if(top(st)=='d')
                    {
                        s.rightShift(b);
                        s.shiftUp(b);
                        pop(st);
                        push('d',st1);
                    }
                    else if(top(st)=='a')
                    {
                        s.leftShift(b);
                        s.shiftUp(b);
                        pop(st);
                        push('a',st1);
                    }
                    else if(top(st)=='s')
                    {
                        if(st.head.a1.length==0&&st.head.a2.length==0)
                        {
                            s.shiftUp(b);
                            pop(st);
                            push('s',st1);
                        }
                        else
                        {
                            push('s',n,s.x,s.y,st1);
                            n=st.head.pos[0];
                            if(n==0)
                                s=new Shape1();
                            else if(n==1)
                                s=new Shape2();
                            else if(n==2)
                                s=new Shape3();
                            else if(n==3)
                                s=new Shape4();
                            else
                                s=new Shape5();
                            for(int u=0;u<4;++u)
                            {
                                s.x[u]=st.head.a1[u];
                                s.y[u]=st.head.a2[u];
                            }
                            pop(st);
                        }
                    }
                    else if(top(st)=='q')
                    {
                        s.antiClockwiseRotate(b);
                        s.shiftUp(b);
                        pop(st);
                        push('q',st1);
                    }
                    else if(top(st)=='w')
                    {
                        s.clockwiseRotate(b);
                        s.shiftUp(b);
                        pop(st);
                        push('w',st1);
                    }
                }
            }
            else if(ch=='d')
            {
                push('d',st);
                if(s.shiftDown(b))
                    s.leftShift(b);
            }
            else if(ch=='a')
            {
                push('a',st);
                if(s.shiftDown(b))
                    s.rightShift(b);
            }
            else if(ch=='s')
            {
                if(s.shiftDown(b))
                {
                    if(!s.boundaryReached(b))
                        push('s',st);  
                    else
                        push('s',n,s.x,s.y,st);
                }
                else
                {
                    for(i=0;i<4&&flag;++i)
                    {
                        b.arr[s.x[i]][s.y[i]]='#';
                    }
                    n=rand.nextInt(10000000)%5;
                    n=1;
                    if(n==0)
                        s=new Shape1();
                    else if(n==1)
                        s=new Shape2();
                    else if(n==2)
                        s=new Shape3();
                    else if(n==3)
                        s=new Shape4();
                    else
                        s=new Shape5();
                    if(b.arr[s.x[0]][s.y[0]]=='#'||b.arr[s.x[1]][s.y[1]]=='#'||b.arr[s.x[2]][s.y[2]]=='#'||b.arr[s.x[3]][s.y[3]]=='#')
                    {
                        System.out.println("GAME OVER");
                        break;                                                                                                 
                    }
                }
            }
            else if(ch=='q')
            {
                push('q',st);
                if(s.shiftDown(b))
                    s.clockwiseRotate(b);
            }
            else if(ch=='w')
            {
                push('w',st);
                if(s.shiftDown(b))
                    s.antiClockwiseRotate(b);
            }
            else if(ch=='e')
                break;
            for(i=0;i<4;++i)
            {
                b.arr[s.x[i]][s.y[i]]='#';
            }
            System.out.print("\033\143");
            for(i=0;i<10;++i)
            {
                for(j=0;j<10;++j)
                {
                    System.out.print(b.arr[i][j]+" ");
                }
                System.out.println();
            }
            if(s.boundaryReached(b)&&ch!='z')
            {
                int min=s.x[0],max=s.x[0];
                for(i=0;i<4;++i)
                {
                    if(min>s.x[i])
                        min=s.x[i];
                    if(max<s.x[i])
                        max=s.x[i];
                    b.arr[s.x[i]][s.y[i]]='#';
                }
                b.checkAndDelete(min,max,st);
                System.out.print("\033\143");
                for(i=0;i<10;++i)
                {
                    for(j=0;j<10;++j)
                    {
                        System.out.print(b.arr[i][j]+" ");
                    }
                    System.out.println();
                }
            }
            else
            {
                for(i=0;i<4;++i)
                    b.arr[s.x[i]][s.y[i]]='#';
            }
        }
    }
}