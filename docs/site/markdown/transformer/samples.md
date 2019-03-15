<head>
    <title>Samples</title>
</head>

# Transformation samples

### Simple case:

~~~Java
public class FromBean {                                     public class ToBean {                           
   private final String name;                                  @NotNull                   
   private final BigInteger id;                                public BigInteger id;                      
   private final List<FromSubBean> subBeanList;                private final String name;                 
   private List<String> list;                                  private final List<String> list;                    
   private final FromSubBean subObject;                        private final List<ImmutableToSubFoo> nestedObjectList;                    
                                                               private ImmutableToSubFoo nestedObject;
   
   // all constructors                                         // all args constructor
   // getters and setters...                                   // getters... 
}                                                               
                                                            }
~~~
And one line code as:
~~~Java
ToBean toBean = beanUtils.getTransformer().transform(fromBean, ToBean.class);
~~~

### Different field names copy:

From class and To class with custom annotation usage and different field names:
~~~Java
public class FromBean {                                     public class ToBean {                           
                                                                                       
   private final String name;                                  private final String differentName;                   
   private final int id;                                       private final int id;                      
   private final List<FromSubBean> subBeanList;                private final List<ToSubBean> subBeanList;                 
   private final List<String> list;                            private final List<String> list;                    
   private final FromSubBean subObject;                        private final ToSubBean subObject;                    
    
   // getters and setters...
                                                               public ToBean(final String differentName, 
                                                                        final int id,
}                                                                       final List<ToSubBean> subBeanList,
                                                                        final List<String> list,
                                                                        final ToSubBean subObject) {
                                                                        this.differentName = differentName;
                                                                        this.id = id;
                                                                        this.subBeanList = subBeanList;
                                                                        this.list = list;
                                                                        this.subObject = subObject; 
                                                                    }
                                                                
                                                                    // getters and setters...           
                                              
                                                                }
~~~
And one line code as:

~~~Java                                                                
beanUtils.getTransformer().withFieldMapping(new FieldMapping("name", "differentName")).transform(fromBean, ToBean.class);                                                               
~~~

### Mapping destination fields with correspondent fields contained inside one of the nested object in the source object:

Assuming that the object `FromSubBean` is declared as follow:
~~~Java
public class FromSubBean {                         
                                                                                       
   private String serialNumber;                 
   private Date creationDate;                    
   
   // getters and setters... 
   
}
~~~
and our source object and destination object are described as follow:
~~~Java
public class FromBean {                                     public class ToBean {                           
                                                                                       
   private final int id;                                       private final int id;                      
   private final String name;                                  private final String name;                   
   private final FromSubBean subObject;                        private final String serialNumber;                 
                                                               private final Date creationDate;                    
   
   // all args constructor                                     // all args constructor
   // getters...                                               // getters... 
   
}                                                           }
~~~
the fields: `serialNumber` and `creationDate` needs to be retrieved from `subObject`, this can be done defining the whole path to the end property:
~~~Java  
FieldMapping serialNumberMapping = new FieldMapping("subObject.serialNumber", "serialNumber");                                                             
FieldMapping creationDateMapping = new FieldMapping("subObject.creationDate", "creationDate");
                                                             
beanUtils.getTransformer()
         .withFieldMapping(serialNumberMapping, creationDateMapping)
         .transform(fromBean, ToBean.class);                                                               
~~~

### Different field names defining constructor args:

~~~Java
public class FromBean {                                     public class ToBean {                           
   private final String name;                                  private final String differentName;                   
   private final int id;                                       private final int id;                      
   private final List<FromSubBean> subBeanList;                private final List<ToSubBean> subBeanList;                 
   private final List<String> list;                            private final List<String> list;                    
   private final FromSubBean subObject;                        private final ToSubBean subObject;                    
   
   // all args constructor
   // getters...
                                                               public ToBean(@ConstructorArg("name") final String differentName, 
                                                                        @ConstructorArg("id") final int id,
}                                                                       @ConstructorArg("subBeanList") final List<ToSubBean> subBeanList,
                                                                        @ConstructorArg(fieldName ="list") final List<String> list,
                                                                        @ConstructorArg("subObject") final ToSubBean subObject) {
                                                                        this.differentName = differentName;
                                                                        this.id = id;
                                                                        this.subBeanList = subBeanList;
                                                                        this.list = list;
                                                                        this.subObject = subObject; 
                                                                    }
                                                                
                                                                    // getters...           
                                              
                                                            }
~~~
And one line code as:
~~~Java
ToBean toBean = beanUtils.getTransformer().transform(fromBean, ToBean.class);
~~~

### Different field names and types applying transformation through lambda function:

~~~Java
public class FromBean {                                     public class ToBean {                           
   private final String name;                                  @NotNull                   
   private final BigInteger id;                                public BigInteger identifier;                      
   private final List<FromSubBean> subBeanList;                private final String name;                 
   private List<String> list;                                  private final List<String> list;                    
   private final FromSubBean subObject;                        private final List<ImmutableToSubFoo> nestedObjectList;                    
   private final String locale;                                private final Locale locale;                    
                                                               private ImmutableToSubFoo nestedObject;
       
   // constructors...                                          // constructors...
   // getters and setters...                                   // getters and setters...
                                                                                                                              
}                                                           }
~~~

~~~Java
FieldTransformer<BigInteger, BigInteger> fieldTransformer = new FieldTransformer<>("identifier", BigInteger::negate);
FieldTransformer<String, Locale> localeTransformer = new FieldTransformer<>("locale", Locale::forLanguageTag);
beanUtils.getTransformer()
    .withFieldMapping(new FieldMapping("id", "identifier"))
    .withFieldTransformer(fieldTransformer).transform(fromBean, ToBean.class)
    .withFieldTransformer(localeTransformer);
~~~

### Assign a default value in case of missing field in the source object:

Assign a default value in case of missing field in the source object:

~~~Java
public class FromBean {                                     public class ToBean {                           
   private final String name;                                  @NotNull                   
   private final BigInteger id;                                public BigInteger id;                      
                                                               private final String name;                 
                                                               private String notExistingField; // this will be null and no exceptions will be raised

   // constructors...                                          // constructors...
   // getters...                                               // getters and setters...

}                                                           }
~~~
And one line code as:
~~~Java
ToBean toBean = beanUtils.getTransformer()
                    .setDefaultValueForMissingField(true).transform(fromBean, ToBean.class);
~~~

### Applying a transformation function in case of missing fields in the source object:

Assign a default value in case of missing field in the source object:

~~~Java
public class FromBean {                                     public class ToBean {                           
   private final String name;                                  @NotNull                   
   private final BigInteger id;                                public BigInteger id;                      
                                                               private final String name;                 
                                                               private String notExistingField; // this will have value: sampleVal
                                                               
   // all args constructor                                     // constructors...
   // getters...                                               // getters and setters...
}                                                           }
~~~
And one line code as:
~~~Java
FieldTransformer<String, String> notExistingFieldTransformer = new FieldTransformer<>("notExistingField", val -> "sampleVal");
ToBean toBean = beanUtils.getTransformer()
                    .withFieldTransformer(notExistingFieldTransformer)
                    .transform(fromBean, ToBean.class);
~~~

### Apply a transformation function on a field contained in a nested object:

This example shows of a lambda transformation function can be applied on a nested object field.

Given:

~~~Java
public class FromBean {                                     public class ToBean {                           
   private final String name;                                  private final String name;                   
   private final FromSubBean nestedObject;                     private final ToSubBean nestedObject;                    

   // all args constructor                                     // all args constructor
   // getters...                                               // getters...
}                                                           }
~~~
and
~~~Java
public class FromSubBean {                                  public class ToSubBean {                           
   private final String name;                                  private final String name;                   
   private final long index;                                   private final long index;                    
}                                                           }
~~~
Assuming that the lambda transformation function should be applied only to field: `name` contained into the `ToSubBean` object, the transformation function has to be defined as 
follow:
~~~Java
FieldTransformer<String, String> nameTransformer = new FieldTransformer<>("nestedObject.name", StringUtils::capitalize);
ToBean toBean = beanUtils.getTransformer()
                    .withFieldTransformer(nameTransformer)
                    .transform(fromBean, ToBean.class);
~~~

### Apply a transformation function on all fields matching with the given one:

This example shows of a lambda transformation function can be applied on all fields matching with the given one independently from their position.

Given:

~~~Java
public class FromBean {                                     public class ToBean {                           
   private final String name;                                  private final String name;                   
   private final FromSubBean nestedObject;                     private final ToSubBean nestedObject;                    

   // all args constructor                                     // all args constructor
   // getters...                                               // getters...
}                                                           }
~~~
and
~~~Java
public class FromSubBean {                                  public class ToSubBean {                           
   private final String name;                                  private final String name;                   
   private final long index;                                   private final long index;                    
   
   // all args constructor                                     // all args constructor
   // getters...                                               // getters...
}                                                           }
~~~
Assuming that the lambda transformation function should be applied only to field: `name` contained into the `ToSubBean` object, the transformation function has to be defined as 
follow:
~~~Java
FieldTransformer<String, String> nameTransformer = new FieldTransformer<>("name", StringUtils::capitalize);
ToBean toBean = beanUtils.getTransformer()
                    .setFlatFieldNameTransformation(true)
                    .withFieldTransformer(nameTransformer)
                    .transform(fromBean, ToBean.class);
~~~

### Static transformer function:

~~~Java
List<FromFooSimple> fromFooSimpleList = Arrays.asList(fromFooSimple, fromFooSimple);
~~~
can be transformed as follow:
~~~Java
Function<FromFooSimple, ImmutableToFooSimple> transformerFunction = BeanUtils.getTransformer(ImmutableToFooSimple.class);
        List<ImmutableToFooSimple> actual = fromFooSimpleList.stream()
                .map(transformerFunction)
                .collect(Collectors.toList());
~~~

### Disable Java Beans validation:

Assuming that the field: `id` in the fromBean instance is null.
~~~Java
public class FromBean {                                     public class ToBean {                           
   private final String name;                                  @NotNull                   
   private final BigInteger id;                                public BigInteger id;                      
                                                               private final String name;

   // all args constructor                                     // all args constructor
   // getters...                                               // getters and setters...
}                                                            }
~~~
adding the following configuration no exception will be thrown:
~~~Java
ToBean toBean = beanUtils.getTransformer()
                     .setValidationDisabled(true)
                     .transform(fromBean, ToBean.class);
~~~

### Copy on an existing instance:

Given:

~~~Java
public class FromBean {                                     public class ToBean {                           
   private final String name;                                  private String name;                   
   private final FromSubBean nestedObject;                     private ToSubBean nestedObject;                    

   // all args constructor                                     // constructor
   // getters...                                               // getters and setters...
}                                                           }
~~~
if you need to perform the copy on an already existing object, just do:
~~~Java
ToBean toBean = new ToBean();
beanUtils.getTransformer().transform(fromBean, toBean);
~~~

More sample beans can be found in the test package: `com.hotels.beans.sample`