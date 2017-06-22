/*
 * Copyright 2010-2017 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.js.incremental

// byte arrays are used to simplify passing to different classloaders
interface IncrementalDataProvider {
    /** gets header metadata (serialized [JsProtoBuf.Header]) from previous compilation */
    val headerMetadata: ByteArray
    /** gets non-dirty package parts metadata (serialized [ProtoBuf.PackageFragment]) from previous compilation */
    val packagePartsMetadata: List<ByteArray>
    /** gets non-dirty package parts binary trees from previous compilation */
    val binaryTrees: List<ByteArray>

    companion object {
        val DO_NOTHING = object : IncrementalDataProvider {
            override val packagePartsMetadata: List<ByteArray>
                get() = emptyList()
            override val binaryTrees: List<ByteArray>
                get() = emptyList()
            override val headerMetadata: ByteArray
                get() = ByteArray(0)
        }
    }
}

class IncrementalDataProviderImpl(
    override val headerMetadata: ByteArray,
    override val packagePartsMetadata: List<ByteArray>,
    override val binaryTrees: List<ByteArray>
) : IncrementalDataProvider