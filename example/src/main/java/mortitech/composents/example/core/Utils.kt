package mortitech.composents.example.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Helpers functions for the example app
 **/

fun createJob(completion: suspend () -> Unit) = CoroutineScope(Job() + Dispatchers.Main).launch { completion() }