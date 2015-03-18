#include <jni.h>
#include <string.h>
#include <stdio.h>
#include <math.h>

#define PRIME_NUMBER "est un nombre premier"
#define NOT_PRIME_NUMBER "n'est pas un nombre premier"

/*
 * Function : detect if the number is a prime number.
 * Return String.
 */
JNIEXPORT jstring JNICALL Java_fr_embeded_sysembdtp1_MainActivity_stringPrimeNumber(JNIEnv *env, jobject obj, jint number){
	jint i, compter, test,limite;
	test = compter = 0;
	limite = sqrt(number) + 1;
	if (number != 0){
		if (number != 1){
			if (number % 2 == 0)
				test = 1;
			else{
				for (i = 3 ; i < limite && ! test; i+=2, compter++)
					if (number % i == 0)
						test = 1;
			}
			if (!test)
				return (*env)->NewStringUTF(env, PRIME_NUMBER);
			else
				return (*env)->NewStringUTF(env, NOT_PRIME_NUMBER);
			return "";
		}
		else
			return (*env)->NewStringUTF(env, NOT_PRIME_NUMBER);
	}
}

/**
 * Function : get all the prime number between 0 and the number.
 * Return an array int[] of prime number.
 */
JNIEXPORT jintArray JNICALL Java_fr_embeded_sysembdtp1_MainActivity_stringPrimeNumberList(JNIEnv *env, jobject obj, jint number){
	int i, j, diviseur, size;
	jintArray result;
	jint* list;
	diviseur = 0;
	size = 0;
	for (i=2; i<number; i++){
		diviseur = 0;
		for (j=1; j<=i; j++){
			if (i%j==0){
				diviseur++;
		    }
		}
		if (diviseur == 2){
			if (size == 0){
				size = 1;
				list = (int *) malloc(size * sizeof(int));
				list[size-1] = i;
		    }
		    else{
		    	size = size+1;
		    	list = realloc(list, size * sizeof(int));
		    	list[size-1] = i;
		    }
		}
	}
	result = (*env)->NewIntArray(env, size);
	(*env)->SetIntArrayRegion(env, result, 0, size, list);
    return result;
}
