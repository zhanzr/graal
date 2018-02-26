/*
 * Copyright (c) 2016, Oracle and/or its affiliates.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of
 * conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or other materials provided
 * with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors may be used to
 * endorse or promote products derived from this software without specific prior written
 * permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS
 * OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE
 * GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.oracle.truffle.llvm.parser.model.blocks;

import java.util.ArrayList;
import java.util.List;

import com.oracle.truffle.llvm.parser.model.symbols.instructions.Instruction;
import com.oracle.truffle.llvm.parser.model.symbols.instructions.TerminatingInstruction;
import com.oracle.truffle.llvm.parser.model.visitors.SymbolVisitor;
import com.oracle.truffle.llvm.runtime.types.symbols.LLVMIdentifier;

public final class InstructionBlock {

    private final int blockIndex;

    private final List<Instruction> instructions = new ArrayList<>();

    private String name = LLVMIdentifier.UNKNOWN;

    public InstructionBlock(int index) {
        this.blockIndex = index;
    }

    public void accept(SymbolVisitor visitor) {
        for (Instruction instruction : instructions) {
            instruction.accept(visitor);
        }
    }

    public void append(Instruction instruction) {
        instructions.add(instruction);
    }

    public int getBlockIndex() {
        return blockIndex;
    }

    public String getName() {
        return name;
    }

    public Instruction getInstruction(int index) {
        return instructions.get(index);
    }

    public int getInstructionCount() {
        return instructions.size();
    }

    public void setName(String name) {
        this.name = LLVMIdentifier.toBlockName(name);
    }

    public void setImplicitName(int label) {
        this.name = LLVMIdentifier.toImplicitBlockName(label);
    }

    public TerminatingInstruction getTerminatingInstruction() {
        assert instructions.get(instructions.size() - 1) instanceof TerminatingInstruction : "last instruction must be a terminating instruction";
        return (TerminatingInstruction) instructions.get(instructions.size() - 1);
    }

    public void replace(Instruction oldInst, Instruction newInst) {
        for (int i = 0; i < instructions.size(); i++) {
            if (instructions.get(i) == oldInst) {
                instructions.set(i, newInst);
            }
        }
    }

    public void set(int index, Instruction instruction) {
        instructions.set(index, instruction);
    }

    public void remove(int index) {
        instructions.remove(index);
    }

    @Override
    public String toString() {
        return String.format("Block (%d) %s", blockIndex, name);
    }
}
