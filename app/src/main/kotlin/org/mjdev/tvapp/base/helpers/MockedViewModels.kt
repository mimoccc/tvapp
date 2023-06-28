//package org.mjdev.tvapp.base.helpers
//
//import android.content.Context
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.viewmodel.CreationExtras
//import kotlin.jvm.internal.Reflection
//import kotlin.reflect.KClass
//import kotlin.reflect.KFunction
//import kotlin.reflect.full.companionObject
//import kotlin.reflect.full.functions
//
//// todo: preview android studio does not support reflection
//// todo: this works runtime only
//class MockedViewModels<VM : ViewModel>(
//    val context: Context
//) : ViewModelProvider.Factory {
//
//    private val KClass<*>.viewModelFncName: String
//        get() = String.format("mock%s", simpleName)
//
//    private val KClass<*>.companion : KClass<*> get() {
//        return companionObject ?: createCompanion()
//    }
//
//    private fun KClass<*>.createCompanion() : KClass<*> {
//        val cname = qualifiedName?.let {
//            String.format("%s.Companion", it)
//        } ?: throw(RuntimeException("Can not create companion class $this"))
//        val clazz =  Class.forName(cname)
//            ?: throw(RuntimeException("Can not create companion class $cname"))
//        return clazz.kt
//    }
//
//    private val KClass<*>.viewModelFnc: KFunction<*>
//        get() {
//            val companion = companion
//            val fnc = companion.functions.firstOrNull { fn -> fn.name == viewModelFncName }
//            return fnc ?: throw (RuntimeException("No function $viewModelFncName exists in $this."))
//        }
//
//    fun KFunction<*>.invoke(context: Context): Any? {
//        val instance = (parameters[0].type.classifier as KClass<*>).objectInstance
//            ?: throw (RuntimeException("Cant create instance of ${parameters[0].type.classifier}"))
//        return call(instance, context)
//    }
//
//    private fun KClass<*>.viewModelInstance(
//        context: Context,
//    ): Any? {
//        return viewModelFnc.invoke(context)
//    }
//
//    fun KClass<*>.get(context: Context): Any {
//        return viewModelInstance(context)
//            ?: throw (RuntimeException("No model instance"))
//    }
//
//    val <T : Any> Class<T>.kt: KClass<T>
//        get() = Reflection.getOrCreateKotlinClass(this) as KClass<T>
//
//    override fun <VM : ViewModel> create(
//        modelClass: Class<VM>,
//        extras: CreationExtras
//    ): VM = modelClass.kt.get(context) as VM
//
//    override fun <VM : ViewModel> create(
//        modelClass: Class<VM>
//    ): VM = modelClass.kt.get(context) as VM
//
//}