public class Polynomial{
	
    double[] coefficients;

    public Polynomial(){
        coefficients = new double[1];
        
    	for(int i=0; i<coefficients.length; i++) {
    		coefficients[i]=0;
    	}
    }
    
    public Polynomial(double[] arrayInitialize) {
     	
    	coefficients = new double[arrayInitialize.length];
    	
    	for(int i=0; i<arrayInitialize.length; i++) {
    		coefficients[i]=arrayInitialize[i];
    	}
    	
    }
    
    public Polynomial add(Polynomial objArg) {
       
	   int minLength=Math.min(this.coefficients.length, objArg.coefficients.length);
	   int maxLength=Math.max(this.coefficients.length, objArg.coefficients.length);
	   
       double arr[] = new double[maxLength];
	   Polynomial newPolyObj = new Polynomial(arr);
       
	   for(int i=0; i<minLength; i++) 
		   newPolyObj.coefficients[i] = this.coefficients[i] + objArg.coefficients[i];
	   
	   if(minLength==this.coefficients.length) {
		   for(int i=minLength; i<maxLength; i++) 
			   newPolyObj.coefficients[i] = objArg.coefficients[i];
	   } else {
		   for(int i=minLength; i<maxLength; i++) 
			   newPolyObj.coefficients[i] = this.coefficients[i];   
	   }
	   
	   return newPolyObj;
    }
   
    public double evaluate(double x) {
	  
	  double result=0;
	  if(coefficients.length>=0) {
		  result=result+coefficients[0];
		  for(int i=1; i<coefficients.length; i++)
			  result = result + (coefficients[i] * Math.pow(x, i));
		  	  
	  }
	  return result;
    }
  
    public boolean hasRoot(double value) {
	  return (evaluate(value)==0.0);
    }


}

