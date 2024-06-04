package com.i.news.domain.usecase

import com.i.news.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(private val localUserManager: LocalUserManager) {

    operator fun invoke(): Flow<Boolean> = localUserManager.readAppEntry()
}