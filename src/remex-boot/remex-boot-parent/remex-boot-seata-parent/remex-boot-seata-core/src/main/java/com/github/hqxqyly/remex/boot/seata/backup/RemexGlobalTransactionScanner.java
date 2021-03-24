package com.github.hqxqyly.remex.boot.seata.backup;
//package com.github.hqxqyly.remex.boot.seata.component.interceptor;
//
//import java.lang.reflect.Method;
//import java.util.HashSet;
//import java.util.Set;
//
//import org.aopalliance.intercept.MethodInterceptor;
//import org.springframework.aop.Advisor;
//import org.springframework.aop.TargetSource;
//import org.springframework.aop.framework.AdvisedSupport;
//import org.springframework.aop.support.AopUtils;
//import org.springframework.beans.BeansException;
//import org.springframework.context.ApplicationContext;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.github.hqxqyly.remex.boot.interfaces.assist.IAssist;
//
//import io.seata.common.util.CollectionUtils;
//import io.seata.config.ConfigurationCache;
//import io.seata.config.ConfigurationChangeListener;
//import io.seata.core.constants.ConfigurationKeys;
//import io.seata.spring.annotation.GlobalLock;
//import io.seata.spring.annotation.GlobalTransactionScanner;
//import io.seata.spring.annotation.GlobalTransactional;
//import io.seata.spring.annotation.GlobalTransactionalInterceptor;
//import io.seata.spring.tcc.TccActionInterceptor;
//import io.seata.spring.util.SpringProxyUtils;
//import io.seata.spring.util.TCCBeanParserUtils;
//import io.seata.tm.api.FailureHandler;
//
//public class RemexGlobalTransactionScanner extends GlobalTransactionScanner implements IAssist {
//	private static final long serialVersionUID = 1L;
//	
//	protected Set<String> REMEX_PROXYED_SET = new HashSet<>();
//	
//	protected MethodInterceptor remexInterceptor;
//	
//	protected MethodInterceptor remexGlobalTransactionalInterceptor;
//	
//	protected FailureHandler remexFailureHandlerHook;
//	
//	protected ApplicationContext remexApplicationContext;
//
//	public RemexGlobalTransactionScanner(String txServiceGroup, int mode) {
//		super(txServiceGroup, mode);
//	}
//
//	public RemexGlobalTransactionScanner(String applicationId, String txServiceGroup,
//			FailureHandler failureHandlerHook) {
//		super(applicationId, txServiceGroup, failureHandlerHook);
//		this.remexFailureHandlerHook = failureHandlerHook;
//	}
//
//	public RemexGlobalTransactionScanner(String applicationId, String txServiceGroup, int mode,
//			FailureHandler failureHandlerHook) {
//		super(applicationId, txServiceGroup, mode, failureHandlerHook);
//		this.remexFailureHandlerHook = failureHandlerHook;
//	}
//
//	public RemexGlobalTransactionScanner(String applicationId, String txServiceGroup, int mode) {
//		super(applicationId, txServiceGroup, mode);
//	}
//
//	public RemexGlobalTransactionScanner(String applicationId, String txServiceGroup) {
//		super(applicationId, txServiceGroup);
//	}
//
//	public RemexGlobalTransactionScanner(String txServiceGroup) {
//		super(txServiceGroup);
//	}
//
//	@Override
//	protected Object wrapIfNecessary(Object bean, String beanName, Object cacheKey) {
//		try {
//            synchronized (REMEX_PROXYED_SET) {
//                if (REMEX_PROXYED_SET.contains(beanName)) {
//                    return bean;
//                }
//                remexInterceptor = null;
//                //check TCC proxy
//                if (TCCBeanParserUtils.isTccAutoProxy(bean, beanName, remexApplicationContext)) {
//                    //TCC interceptor, proxy bean of sofa:reference/dubbo:reference, and LocalTCC
//                	remexInterceptor = new TccActionInterceptor(TCCBeanParserUtils.getRemotingDesc(beanName));
//                    ConfigurationCache.addConfigListener(ConfigurationKeys.DISABLE_GLOBAL_TRANSACTION,
//                        (ConfigurationChangeListener)remexInterceptor);
//                } else {
//                    Class<?> serviceInterface = SpringProxyUtils.findTargetClass(bean);
//                    Class<?>[] interfacesIfJdk = SpringProxyUtils.findInterfaces(bean);
//
//                    if (!remexExistsAnnotation(new Class[]{serviceInterface})
//                        && !remexExistsAnnotation(interfacesIfJdk)) {
//                        return bean;
//                    }
//
//                    if (remexInterceptor == null) {
//                        if (remexGlobalTransactionalInterceptor == null) {
//                        	remexGlobalTransactionalInterceptor = new GlobalTransactionalInterceptor(remexFailureHandlerHook);
//                            ConfigurationCache.addConfigListener(
//                                ConfigurationKeys.DISABLE_GLOBAL_TRANSACTION,
//                                (ConfigurationChangeListener)remexGlobalTransactionalInterceptor);
//                        }
//                        remexInterceptor = remexGlobalTransactionalInterceptor;
//                    }
//                }
//
//                getLogger().info("Bean[{}] with name [{}] would use interceptor [{}]", bean.getClass().getName(), beanName, remexInterceptor.getClass().getName());
//                if (!AopUtils.isAopProxy(bean)) {
//                    bean = super.wrapIfNecessary(bean, beanName, cacheKey);
//                } else {
//                    AdvisedSupport advised = SpringProxyUtils.getAdvisedSupport(bean);
//                    Advisor[] advisor = buildAdvisors(beanName, getAdvicesAndAdvisorsForBean(null, null, null));
//                    for (Advisor avr : advisor) {
//                        advised.addAdvisor(0, avr);
//                    }
//                }
//                REMEX_PROXYED_SET.add(beanName);
//                return bean;
//            }
//        } catch (Exception exx) {
//            throw new RuntimeException(exx);
//        }
//	}
//	
//	protected boolean remexExistsAnnotation(Class<?>[] classes) {
//        if (CollectionUtils.isNotEmpty(classes)) {
//            for (Class<?> clazz : classes) {
//                if (clazz == null) {
//                    continue;
//                }
//                GlobalTransactional trxAnno = clazz.getAnnotation(GlobalTransactional.class);
//                if (trxAnno != null) {
//                    return true;
//                }
//                Method[] methods = clazz.getMethods();
//                for (Method method : methods) {
//                    trxAnno = method.getAnnotation(GlobalTransactional.class);
//                    if (trxAnno != null) {
//                        return true;
//                    }
//                    
//                    Transactional transactional = method.getAnnotation(Transactional.class);
//                    if (transactional != null) {
//                    	return true;
//                    }
//
//                    GlobalLock lockAnno = method.getAnnotation(GlobalLock.class);
//                    if (lockAnno != null) {
//                        return true;
//                    }
//                }
//            }
//        }
//        return false;
//    }
//	
//	@Override
//	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//		this.remexApplicationContext = applicationContext;
//		super.setApplicationContext(applicationContext);
//	}
//	
//	@SuppressWarnings("rawtypes")
//	@Override
//	protected Object[] getAdvicesAndAdvisorsForBean(Class beanClass, String beanName, TargetSource customTargetSource)
//			throws BeansException {
//		return new Object[]{remexInterceptor};
//	}
//}
