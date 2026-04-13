package com.massana2110.pokeapp.ui.components.spacer

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ColumnScope.Spacer4() = Spacer(modifier = Modifier.height(4.dp))

@Composable
fun ColumnScope.Spacer8() = Spacer(modifier = Modifier.height(8.dp))

@Composable
fun ColumnScope.Spacer16() = Spacer(modifier = Modifier.height(16.dp))

@Composable
fun ColumnScope.Spacer1f() = Spacer(modifier = Modifier.weight(1f))

@Composable
fun RowScope.Spacer8() = Spacer(modifier = Modifier.width(8.dp))