package mew;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

public class sale_ordernumber {
	// ʹ�õ���ģʽ��������ֱ�Ӵ���ʵ��
    public sale_ordernumber() {}
    // ����һ����ʵ����������Ҫ�õ�ʱ��Ÿ�ֵ
    private static sale_ordernumber instance = null;
    // ����ģʽ--����ģʽ
    public static synchronized sale_ordernumber getInstance() {
        if (instance == null) {
        	instance = new sale_ordernumber();
        }
        return instance;
    }
    // ȫ��������
    private static int count = 1;
    // ��ʽ����ʱ���ַ���
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    // ��ȡ��ǰʱ��������ʱ��������ַ���
    private static String getNowDateStr() {
        return sdf.format(new Date());
    }
    // ��¼��һ�ε�ʱ�䣬�����ж��Ƿ���Ҫ����ȫ����
    private static String now = null;
    //����������
    private final static ReentrantLock lock=new ReentrantLock();
    //���õķ���
    public static String GetRandom(){
    	String Newnumber=null;
    	String dateStr=getNowDateStr();
    	lock.lock();//����
    	//�ж���ʱ���Ƿ���ͬ
    	if (dateStr.equals(now)) {
    		try {
       		 if (count >= 10000)
                {
                    count = 1;
                }
           	 if (count<10) {
           		 Newnumber = "N" + getNowDateStr()+"000"+count;
       		}else if (count<100) {
       			Newnumber = "N" + getNowDateStr()+"00"+count;
       		}else if(count<1000){
       			 Newnumber = "N" + getNowDateStr()+"0"+count;
       		}else  {
       			 Newnumber = "N" + getNowDateStr()+count;
       		}
                count++;
   		} catch (Exception e) {
   		}finally{
   			lock.unlock();
   		}
		}else{
			count=1;
			now =getNowDateStr();
			try {
				 if (count >= 10000)
	                {
	                    count = 1;
	                }
	           	 if (count<10) {
	           		 Newnumber = "N" + getNowDateStr()+"000"+count;
	       		}else if (count<100) {
	       			Newnumber = "N" + getNowDateStr()+"00"+count;
	       		}else if(count<1000){
	       			 Newnumber = "N" + getNowDateStr()+"0"+count;
	       		}else  {
	       			 Newnumber = "N" + getNowDateStr()+count;
	       		}
	                count++;
			} catch (Exception e) {
			}finally{
				lock.unlock();
			}
		}
         return Newnumber;//���ص�ֵ
    }

}
